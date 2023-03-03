<template>
  <div id="shortTimeEnergy" class="shortTimeEnergy"></div>
</template>

<script lang="js">
import * as echarts from 'echarts';
import {defineComponent, onMounted} from "vue";
import {Tool} from "@/util/tool";

export default defineComponent({
  name: "shortTimeEnergy",
  setup: function () {
    onMounted(async () => {
      let websocket;
      let token;
      let vibration = [];
      const win = [0.0800,0.1876,0.4601,0.7700,0.9723,0.9723,0.7700,0.4601,0.1876,0.0800];
      const wlen = 10;
      const inc = 4;
      var chartDom = document.getElementById('shortTimeEnergy');
      var myChart = echarts.init(chartDom);
      let time = new Date();
      let data = {
        categoryData:[],
        valueData:[]
      } ;
      let option = {
        title: {
          text: '海缆振动短时能量值',
          textStyle: {
            color: '#ffffff',
            fontFamily: '宋体',
          },
        },
        toolbox: {
          feature: {
            dataZoom: {
              yAxisIndex: false
            },
            saveAsImage: {
              pixelRatio: 2
            }
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: 90,
          bottom: 90
        },
        dataZoom: [
          {
            type: 'inside'
          },
          {
            type: 'slider'
          }
        ],
        xAxis: {
          data: data.categoryData,
          silent: false,
          splitLine: {
            show: false
          },
          splitArea: {
            show: false
          }
        },
        yAxis: {
          name: '能量(dB)',
          nameLocation: 'center',
          nameGap: 30,
          splitArea: {
            show: false
          },
          scale:true,
        },
        series: [
          {
            type: 'line',
            data: data.valueData,
            large: true
          }
        ]
      };
      time -= 4920
      function countEnergy(list) {
        var a = 0;
        var ans = 0;
        var c = 0;
        var m = 0;
        var k = 0;
        var v = 0;
        var s = 0;
        var M = list;
        for (let i = 0;i<500;i++){
          ans=ans+list[i];
          v += 1;
          if((i+1)%60==0){
            c=ans/60;
            ans=0;
            for(k=v-60;k<v;k++){
              M[k]=M[k]-c;
            }
            s=i+1;
          }
        }
        var sad=0;
        for(let i =s;i<500;i++){
          sad=sad+M[i];
        }
        console.log('sad:',sad);
        var sade=sad/(500-s);
        for(let i=s;i<500;i++){
          M[i]=M[i]-sade;
        }

        var f = Array(123);
        for (let i = 0;i<123;i++){
          f[i]=Array(10);
        }
        var indf =Array(123);
        for(let i=0;i<123;i++){
          indf[i]=i*inc;
        }
        var inds =Array(10);
        for (let i=0;i<10;i++){
          inds[i]=i+1;
        }
        for(let i=0;i<123;i++){
          for(let j=0;j<10;j++){
            f[i][j]=M[indf[i]+inds[j]-1];
          }
        }
        for(let i=0;i<123;i++){
          for(let j=0;j<10;j++){
            f[i][j]=f[i][j]*win[j];
          }
        }
        // var frames = Array(123);
        // for (let i =0;i<frames.length;i++){
        //   frames[i]=Array(10);
        // }
        // var startIndex=0;
        // var endIndex=0;
        // for(let i = 0;i<123;i++){
        //   startIndex = i*inc;
        //   endIndex = startIndex+wlen;
        //   if(endIndex>M.length){
        //     endIndex = M.length;
        //   }
        //   // var frame = [];
        //   for(let j=0;j<10;j++){
        //     if(startIndex+i<M.length){
        //       frames[i][j]=M[startIndex+j];
        //       // frame.push(M[startIndex+i]);
        //     }else {
        //       frames[i][j]=0;
        //       // frame.push(0);
        //     }
        //   }
        //   // frames.push(frame);
        // }
        // console.log('frames:',frames);
        var temp = 0;
        var energy = Array(123);
        for(let i=0;i<123;i++){
          energy[i]=0;
        }
        for(let i=0;i<123;i++){
          for (let j =0;j<10;j++){
            temp=f[i][j]*f[i][j];
            energy[i]=energy[i]+temp;
            temp=0;
          }
        }
        const categoryData = [];
        const valueData = [];
        for (let i = 0; i < 123; i++) {
          categoryData.push(
              echarts.format.formatTime('yyyy-MM-dd\nhh:mm:ss', time, false)
          );
          valueData.push(energy[i]);
          time += 40
        }
        return {
          categoryData: categoryData,
          valueData: valueData
        };
      }
      const onOpen = () =>{
        console.log('WebSocket连接成功，状态码：',websocket.readyState)
      };
      const onMessage = function (msg){
        vibration = JSON.parse(msg.data)
        data = countEnergy(vibration);
        option.xAxis.data = data.categoryData;
        option.series[0].data = data.valueData;
        myChart.setOption(option)
        console.log(vibration)
      };
      const onError = ()=>{
        console.log('WebSocket连接错误，状态码：', websocket.readyState)
      };
      const onClose = ()=>{
        console.log('WebSocket连接关闭，状态码：',websocket.readyState)
      };
      const initWebSocket = () =>{
        //连接成功
        websocket.onOpen = onOpen;
        // 收到消息的回调
        websocket.onmessage = onMessage;
        // 连接错误
        websocket.onerror = onError;
        // 连接关闭的回调
        websocket.onClose = onClose;
      }
      if('WebSocket' in window){

        token = Tool.uuid(10);
        console.log("******",token)
        //连接地址：ws://127.0.0.1:8080/vibrationWaveWs/xxx
        websocket = new WebSocket(process.env.VUE_APP_WS_SERVER + '/vibrationWaveWs/' + token);
        initWebSocket()
      }else{
        alert('当前浏览器 不支持')
      }
    })
  },
})
</script>

<style scoped>

</style>