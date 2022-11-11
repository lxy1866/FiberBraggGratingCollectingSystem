<!--<template>-->
<!--  <a-layout>-->
<!--    <a-layout-content :style="{background: '#fff'}">-->
<!--        <p style="color: red; font-family: 'Adobe 宋体 Std L';">弧长长度为：{{arcLength}}</p>-->
<!--        <p style="color: red; font-family: 'Adobe 宋体 Std L';">各点的曲率值：{{curvature}}</p>-->
<!--        <div id="twoDimension" class="twoDimension-chart"></div>-->
<!--    </a-layout-content>-->
<!--  </a-layout>-->
<!--</template>-->
<!--<script lang="js">-->
<!--import {all, create} from 'mathjs'-->
<!--import {computed, defineComponent, onMounted, ref} from "vue";-->
<!--import * as echarts from 'echarts';-->
<!--import axios from "axios";-->

<!--const config = {}-->
<!--const math = create(all, config)-->
<!--let arcLength = ref();-->
<!--let curvature = ref();-->
<!--function diff(src){-->
<!--  let result = [];-->
<!--  for(let i = 0; i < src.length-1; i++){-->
<!--    result[i] = src[i+1] - src[i];-->
<!--  }-->
<!--  return result;-->
<!--}-->
<!--function angleToRadian(src){-->
<!--  for(let i = 0; i < src.length; i++){-->
<!--    src[i] = src[i]/180*Math.PI;-->
<!--  }-->
<!--  return src-->
<!--}-->
<!--function handleGetArgLength(){-->
<!--  return axios.get("/arc/length")-->
<!--}-->
<!--function generateData(arcLength) {-->
<!--    //let l = arcLength//0.3-->
<!--    let l = 0.3-->
<!--    const angle_raw = [0, 30, 30, -30, 30, 30, -30, -30, -30, 0]-->
<!--    const angle1 = angleToRadian(diff(angle_raw)); //通过曲率的倒数即大弧的半径求得圆心角-->

<!--    const l_inter = l / 10 //-->
<!--    let p_before_test = math.matrix([0, 0, 0]);//-->
<!--    let H1 = math.matrix([[1, 0, 0], [0, 1, 0], [0, 0, 1]]) //-->
<!--    let p_test1 = math.matrix() //-->
<!--    for (let row = 0; row < angle1.length; row++) {-->
<!--      let R1 = math.abs(l / angle1[row])//大弧的半径 //通过曲率求得-->
<!--      let p_temp = math.matrix()-->
<!--      for (let i = 0; i < 10; i++) {-->
<!--        let angle = angle1[row] / 10-->
<!--        if (angle === 0) {-->
<!--          p_temp = math.transpose(math.matrix([l_inter, 0, 0]))-->
<!--        } else if (angle > 0) {-->
<!--          p_temp = math.transpose(math.matrix([R1 * Math.sin(angle), 0, -(R1 - R1 * Math.cos(angle))]))-->
<!--        } else {-->
<!--          p_temp = math.transpose(math.matrix([-R1 * Math.sin(angle), 0, (R1 - R1 * Math.cos(angle))]))-->
<!--        }-->
<!--        p_before_test = math.transpose(p_before_test)-->
<!--        let p_after_test = math.add(math.multiply(H1, p_temp), p_before_test)-->
<!--        //将矩阵拼接-->
<!--        p_test1 = math.matrix(math.concat(p_test1, p_after_test))-->
<!--        p_before_test = math.matrix(math.transpose(p_after_test));-->
<!--        let delta_R = [[Math.cos(angle), 0, Math.sin(angle)], [0, 1, 0], [-Math.sin(angle), 0, Math.cos(angle)]]-->
<!--        H1 = math.multiply(H1, delta_R);-->
<!--      }-->
<!--    }-->
<!--    let data = [];-->
<!--    data.push([0, 0])-->
<!--    console.log("p_test1",p_test1)-->
<!--    // console.log(p_test1.get([0]), p_test1.get([2]))-->

<!--    for (let i = 0; i < 10 * angle1.length; i++) {-->
<!--      data.push([p_test1.get([3 * i]), p_test1.get([3 * i + 2])]);-->
<!--    }-->
<!--    console.log(data)-->
<!--    return data;-->
<!--}-->
<!--export default defineComponent({-->
<!--  setup () {-->
<!--    onMounted(async ()=> {-->
<!--      const chartDom = document.getElementById('twoDimension');-->
<!--      // const {data} = await handleGetArgLength();-->
<!--      // const length = data.content-->
<!--      // arcLength.value = data.content-->
<!--      //var EChartsOption = echarts.EChartsOption;-->

<!--      const myChart = echarts.init(chartDom);-->
<!--      let option;// = EChartsOption;-->

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
<!--      //   //console.log("WebSocket收到消息：",event.data);-->
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
<!--  width: 500px;-->
<!--  height: 500px;-->
<!--  display: flex;-->
<!--}-->
<!--</style>-->
<template>
  <a-layout>
    <a-layout-content :style="{background: '#fff'}">
      <!--      <p style="color: red; font-family: 'Adobe 宋体 Std L';">弧长长度为：{{arcLength}}</p>-->
      <!--      <p style="color: red; font-family: 'Adobe 宋体 Std L';">各点的曲率值：{{curvature}}</p>-->
      <div id="twoDimension" class="twoDimension"></div>
    </a-layout-content>
  </a-layout>
</template>
<script lang="js">

import {all, create} from 'mathjs'
import { defineComponent, onMounted, ref} from "vue";
import * as echarts from 'echarts';
import axios from "axios";
import interp1 from 'interp1';
const config = {}
const math = create(all, config)
function queryLast15() {
  return axios.get("/txt/queryLast15")
}
const Spline = require('cubic-spline');
async function generateData(queryLast15Result) {
  /*******15*3******/
  queryLast15Result = queryLast15Result.data.content
  //console.log("queryLast15Result", queryLast15Result)
  let data = Array.from(Array(15), () => new Array(3));
  for (let i = 0; i < 15; i++) {
    data[i][0] = queryLast15Result[i].aX;
    data[i][1] = queryLast15Result[i].aY;
    data[i][2] = queryLast15Result[i].aZ;
  }
  let datajs1 = data;

  let angleXY = []
  for (let i = 0; i < 15; i++) {
    angleXY[i] = math.acos((datajs1[i][1]) / Math.sqrt(Math.pow(datajs1[i][0], 2) + Math.pow(datajs1[i][1], 2) + Math.pow(datajs1[i][2], 2)))
  }
  let angle_raw3 = angleXY;
  let l3 = 1;
  let p_before_test3 = math.matrix([0, 0, 0]);
  let H3 = math.matrix([[1, 0, 0], [0, 1, 0], [0, 0, 1]]);
  let p_test3 = Array.from(Array(3), () => new Array(15));
  p_test3 = math.subset(p_test3, math.index(0,0), 0);
  p_test3 = math.subset(p_test3, math.index(1,0), 0);
  p_test3 = math.subset(p_test3, math.index(2,0), 0);
  let angle3 = [];
  let p_after_test3 = math.matrix();
  let p_temp3 =  math.matrix();
  for (let row3 = 0; row3 < angle_raw3.length - 1; row3++) {
    angle3[row3] = (angle_raw3[row3 + 1] - angle_raw3[row3]) / 180 * Math.PI;
    let R3 = Math.abs(l3 / angle3[row3])
    p_temp3 = math.matrix();
    if (angle3[row3] === 0) {
      p_temp3 = math.matrix([l3, 0, 0]);
    } else if (angle3[row3] > 0) {
      p_temp3 =  math.transpose(math.matrix([R3 * Math.sin(angle3[row3]), 0, -(R3 - R3 * Math.cos(angle3[row3]))]));
    } else{
      p_temp3 =  math.transpose(math.matrix([-R3 * Math.sin(angle3[row3]), 0, (R3 - R3 * Math.cos(angle3[row3]))]));
    }
    p_after_test3 = math.add(math.multiply(H3, p_temp3), math.transpose(p_before_test3));
    p_test3 = math.subset(p_test3, math.index(0,row3+1), math.subset(p_after_test3, math.index(0)));
    p_test3 = math.subset(p_test3, math.index(1,row3+1), math.subset(p_after_test3, math.index(1)));
    p_test3 = math.subset(p_test3, math.index(2, row3+1), math.subset(p_after_test3,math.index(2)));
    p_before_test3 = math.transpose(p_after_test3);
    let delta_R2 = math.matrix([[math.cos(angle3[row3]), 0, math.sin(angle3[row3])],
      [0, 1, 0], [-math.sin(angle3[row3]), 0, math.cos(angle3[row3])]]);
    H3 = math.multiply(H3, delta_R2);
  }
  let addMatrix = math.matrix([[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0.015, 0.016, 0.013, 0.011]])
  p_test3 = math.add(p_test3, addMatrix);
  // console.log("angle3", angle3)
  // console.log("p_test3",p_test3)
  // console.log("p_before_test3",p_before_test3)
  // console.log("p_after_test3",  p_after_test3)
  // console.log("p_temp3", p_temp3)
  //let x13 = Math.min(p_test3[1]):k13:Math.max(p_test3[1]);
  //p_test3[1]实际x轴范围 p_test3[3]：函数   x13 实际要插值的范围  spline：三次样条插值
  //let y13 = math.inte(p_test3[1],p_test3[3],x13,'spline');
  let xs = []
  let row0 = math.subset(p_test3, math.index(0,[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14]));
  for(let i = 0; i < 15; i++){
    xs.push(row0.get([0,i]));
  }
  xs = xs.map(Number);
  //console.log("xs", xs)
  let row2 = math.subset(p_test3, math.index(2,[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14]));
  let vs = [];
  for(let i = 0; i < 15; i++){
    vs.push(row2.get([0,i]));
  }
  //console.log("vs", vs)
  let k13 = (Math.max(...xs) - Math.min(...xs)) / 1000;
  //console.log("k13", k13)
  let xqs = [];
  for(let i = Math.min(...xs); i < Math.max(...xs); i = i + k13 ){
    xqs.push(i)

  }
  //let vqs = interp1(xs, vs, xqs, "nearest");
  const spline = new Spline(xs, vs);

  let resultData = [];
  // for (let i = 0; i < xqs.length; i++) {
  //   let row = []
  //   row.push(xqs[i]);
  //   row.push(vqs[i]);
  //   resultData.push(row);
  // }
  for (let i = 0; i < 15; i++) {
    let row = []
    row.push(xs[i]);
    row.push(vs[i]);
    resultData.push(row);
  }
  // for (let i = 0; i < xqs.length; i++) {
  //   let row = []
  //   row.push(xqs[i]);
  //   row.push(spline.at(xqs[i]));
  //   resultData.push(row);
  // }
  //console.log("resultData", resultData)
  return resultData;
}
export default defineComponent({
  setup () {
    onMounted(async ()=> {
      const chartDom = document.getElementById('twoDimension');
      const myChart = echarts.init(chartDom);
      let queryLast15Result = []

      queryLast15Result = await queryLast15()
      let createTime = queryLast15Result.data.content[14].createTime
      let option = {
        animation: false,
        grid: {
          top: 40,
          left: 50,
          right: 40,
          bottom: 50
        },
        xAxis: {
          name: 'x',
          minorTick: {
            show: true
          },
          minorSplitLine: {
            show: true
          }
        },
        yAxis: {
          name: 'y',
          minorTick: {
            show: true
          },
          minorSplitLine: {
            show: true
          }
        },
        dataZoom: [
          {
            show: true,
            type: 'inside',
            filterMode: 'none',
            xAxisIndex: [0],
            startValue: -20,
            endValue: 20
          },
          {
            show: true,
            type: 'inside',
            filterMode: 'none',
            yAxisIndex: [0],
            startValue: -20,
            endValue: 20
          }
        ],
        series: [
          {
            type: 'line',
            smooth: 'spline',
            clip: true,
            symbol: "emptyCircle",
            symbolSize: 4,
            symbolRotate: null,
            showSymbol: true,
            data: await generateData(queryLast15Result)
          }
        ]
      };
      await myChart.setOption(option);
    })
    return{

    }
  }
})
</script>
<style>
.twoDimension{
  width: 500px;
  height: 500px;
  display: flex;
}
</style>

