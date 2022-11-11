<template>
  <a-layout>
    <a-layout-content :style="{background: '#fff'}">
<!--      <p style="color: red; font-family: 'Adobe 宋体 Std L';">弧长长度为：{{arcLength}}</p>-->
<!--      <p style="color: red; font-family: 'Adobe 宋体 Std L';">各点的曲率值：{{curvature}}</p>-->
      <div id="threeDimension" class="threeDimension-chart"></div>
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
      const chartDom = document.getElementById('threeDimension');
      const myChart = echarts.init(chartDom);
      let queryLast15Result = []
      queryLast15Result = await queryLast15()
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
.threeDimension-chart{
  width: 500px;
  height: 500px;
  display: flex;
}
</style>
