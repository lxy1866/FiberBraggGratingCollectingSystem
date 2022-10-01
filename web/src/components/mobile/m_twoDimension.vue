<!--<template>-->
<!--  <p style="color: red; font-family: 'Adobe 宋体 Std L';">弧长长度为：{{arcLength}}</p>-->
<!--  <p style="color: red; font-family: 'Adobe 宋体 Std L';">各点的曲率值：{{curvature}}</p>-->
<!--  <div id="twoDimension" class="twoDimension-chart"></div>-->
<!--</template>-->
<!--<script lang="ts">-->
<!--import {all, create} from 'mathjs'-->
<!--import { defineComponent, onMounted, ref} from "vue";-->
<!--import * as echarts from 'echarts';-->
<!--import axios from "axios";-->

<!--const config = {}-->
<!--const math = create(all, config)-->
<!--let arcLength = ref();-->
<!--let curvature = ref();-->
<!--function diff(src:number[]){-->
<!--  let result = [];-->
<!--  for(let i = 0; i < src.length-1; i++){-->
<!--    result[i] = src[i+1] - src[i];-->
<!--  }-->
<!--  return result;-->
<!--}-->
<!--function angleToRadian(src:number[]){-->
<!--  for(let i = 0; i < src.length; i++){-->
<!--    src[i] = src[i]/180*Math.PI;-->
<!--  }-->
<!--  return src-->
<!--}-->
<!--function handleGetArgLength(){-->
<!--  return axios.get("/arc/length")-->
<!--}-->
<!--function generateData(arcLength:any) {-->
<!--  //let l = arcLength//0.3-->
<!--  let l = 0.3-->
<!--  const angle_raw = [0, 30, 30, -30, 30, 30, -30, -30, -30, 0]-->
<!--  const angle1 = angleToRadian(diff(angle_raw)); //通过曲率的倒数即大弧的半径求得圆心角-->

<!--  const l_inter = l / 10 //-->
<!--  let p_before_test = math.matrix([0, 0, 0]);//-->
<!--  let H1 = math.matrix([[1, 0, 0], [0, 1, 0], [0, 0, 1]]) //-->
<!--  let p_test1 = math.matrix() //-->
<!--  for (let row = 0; row < angle1.length; row++) {-->
<!--    let R1 = math.abs(l / angle1[row])//大弧的半径 //通过曲率求得-->
<!--    let p_temp = math.matrix()-->
<!--    for (let i = 0; i < 10; i++) {-->
<!--      let angle = angle1[row] / 10-->
<!--      if (angle == 0) {-->
<!--        p_temp = math.transpose(math.matrix([l_inter, 0, 0]))-->
<!--      } else if (angle > 0) {-->
<!--        p_temp = math.transpose(math.matrix([R1 * Math.sin(angle), 0, -(R1 - R1 * Math.cos(angle))]))-->
<!--      } else {-->
<!--        p_temp = math.transpose(math.matrix([-R1 * Math.sin(angle), 0, (R1 - R1 * Math.cos(angle))]))-->
<!--      }-->
<!--      p_before_test = math.transpose(p_before_test)-->
<!--      let p_after_test = math.add(math.multiply(H1, p_temp), p_before_test)-->
<!--      //将矩阵拼接-->
<!--      p_test1 = math.matrix(math.concat(p_test1, p_after_test))-->
<!--      p_before_test = math.matrix(math.transpose(p_after_test));-->
<!--      let delta_R = [[Math.cos(angle), 0, Math.sin(angle)], [0, 1, 0], [-Math.sin(angle), 0, Math.cos(angle)]]-->
<!--      H1 = math.multiply(H1, delta_R);-->
<!--    }-->
<!--  }-->
<!--  let data = [];-->
<!--  data.push([0, 0])-->
<!--  // console.log(p_test1)-->
<!--  // console.log(p_test1.get([0]), p_test1.get([2]))-->
<!--  for (let i = 0; i < 10 * angle1.length; i++) {-->
<!--    data.push([p_test1.get([3 * i]), p_test1.get([3 * i + 2])]);-->
<!--  }-->
<!--  // console.log(data)-->
<!--  return data;-->
<!--}-->
<!--export default defineComponent({-->
<!--  setup () {-->
<!--    onMounted(async ()=> {-->
<!--      const chartDom = document.getElementById('twoDimension')!;-->
<!--      // const {data} = await handleGetArgLength();-->
<!--      // const length = data.content-->
<!--      // arcLength.value = data.content-->
<!--      type EChartsOption = echarts.EChartsOption;-->

<!--      const myChart = echarts.init(chartDom);-->
<!--      let option: EChartsOption;-->

<!--      option = {-->
<!--        animation: false,-->
<!--        grid: {-->
<!--          top: 40,-->
<!--          left: 50,-->
<!--          right: 40,-->
<!--          bottom: 50-->
<!--        },-->
<!--        xAxis: {-->
<!--          name: 'x',-->
<!--          minorTick: {-->
<!--            show: true-->
<!--          },-->
<!--          minorSplitLine: {-->
<!--            show: true-->
<!--          }-->
<!--        },-->
<!--        yAxis: {-->
<!--          name: 'y',-->
<!--          minorTick: {-->
<!--            show: true-->
<!--          },-->
<!--          minorSplitLine: {-->
<!--            show: true-->
<!--          }-->
<!--        },-->
<!--        dataZoom: [-->
<!--          {-->
<!--            show: true,-->
<!--            type: 'inside',-->
<!--            filterMode: 'none',-->
<!--            xAxisIndex: [0],-->
<!--            startValue: -20,-->
<!--            endValue: 20-->
<!--          },-->
<!--          {-->
<!--            show: true,-->
<!--            type: 'inside',-->
<!--            filterMode: 'none',-->
<!--            yAxisIndex: [0],-->
<!--            startValue: -20,-->
<!--            endValue: 20-->
<!--          }-->
<!--        ],-->
<!--        series: [-->
<!--          {-->
<!--            type: 'line',-->
<!--            smooth: true,-->
<!--            showSymbol: false,-->
<!--            clip: true,-->
<!--            data: generateData(length)-->
<!--          }-->
<!--        ]-->
<!--      };-->

<!--      option && myChart.setOption(option);-->
<!--      // const onOpen = () => {-->
<!--      //   console.log('WebSocket连接成功，状态码：', websocket.readyState)-->
<!--      // };-->
<!--      // const onMessage = function (msg: any) {-->
<!--      //   console.log("WebSocket收到消息：",event.data);-->
<!--      //   let data = JSON.parse(msg.data);-->
<!--      //   data = data[1];//应变值-->
<!--      //   //换算成曲率值 刷新到curvature上 传参-->
<!--      //-->
<!--      //   //换算成大弧半径 这部分在generateData写-->
<!--      //-->
<!--      //   //换算成大弧对应的圆心角  这部分在generateData写-->
<!--      //-->
<!--      //   //给generateData中的angle1赋值  这部分在generateData写-->
<!--      //-->
<!--      //   //myChart.setOption<echarts.EChartsOption>(option);-->
<!--      // };-->
<!--      // const onError = () => {-->
<!--      //   console.log('WebSocket连接错误，状态码：', websocket.readyState)-->
<!--      // };-->
<!--      // const onClose = () => {-->
<!--      //   console.log('WebSocket连接关闭，状态码：', websocket.readyState)-->
<!--      // };-->
<!--      // const initWebSocket = () => {-->
<!--      //   //连接成功-->
<!--      //   websocket.onOpen = onOpen;-->
<!--      //   // 收到消息的回调-->
<!--      //   websocket.onmessage = onMessage;-->
<!--      //   // 连接错误-->
<!--      //   websocket.onerror = onError;-->
<!--      //   // 连接关闭的回调-->
<!--      //   websocket.onClose = onClose;-->
<!--      // }-->
<!--      // if ('WebSocket' in window) {-->
<!--      //   token = Tool.uuid(10);-->
<!--      //   //连接地址：ws://127.0.0.1:8080/ws/xxx-->
<!--      //   websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/ws/' + token);-->
<!--      //   initWebSocket()-->
<!--      // } else {-->
<!--      //   alert('当前浏览器 不支持')-->
<!--      // }-->
<!--    })-->
<!--    return{-->
<!--      arcLength,-->
<!--      curvature-->
<!--    }-->
<!--  }-->
<!--})-->
<!--</script>-->
<!--<style>-->
<!--.twoDimension-chart{-->
<!--  width: 300px;-->
<!--  height: 300px;-->
<!--  display: flex;-->
<!--}-->
<!--</style>-->
