const config1={
}
const config2={

    header: ['项目名称', '项目地点', '监测等级','安全状况','预警信息'],
    data: [
        ['洪鹤大桥', '珠海市香洲区', '一级','安全','暂无'],
        ['甲子塘桥', '深圳市光明区', '一级','安全','暂无'],
        ['求水顶隧道', '深圳市光明区', '一级','安全','暂无'],
        ['观光路边城', '深圳市光明区', '一级','安全','暂无'],
    ],
    index: false,
    columnWidth: [90,90,90],
    align: ['center'],
    carousel: 'page'
}
const config3={
    header: ['传感器编号', '应变值', '时间'],
    data: [
        ['洪鹤大桥', '支座位移', '1.2mm'],
        ['洪鹤大桥', '应变', '3.6μξ'],
        ['甲子塘桥', '应变', '3.6μξ'],
        ['甲子塘桥', '挠度', '3.0mm'],
        ['求水顶隧道', '挠度', '0,01mm'],
        ['求水顶隧道', '隧道收敛', '4.7m'],
        ['观光路边城', '边坡沉降', '0.2mm'],
        ['观光路边城', '边坡漂移', '0.2mm'],
    ],
    index: false,
    columnWidth: [],
    align: ['center'],
    carousel: 'page'
}
const config4={
}
const config5={

    points: [
        {

            name: '郑州',
            coordinate: [0.48, 0.35],
            icon: {

                src: require('@/assets/map.png'),
                width: 30,
                height: 30
            },
            text: {

                color: '#fb7293'
            }
        },
        {

            name: '新乡',
            coordinate: [0.52, 0.23]
        },
        {

            name: '焦作',
            coordinate: [0.43, 0.29]
        },
        {

            name: '开封',
            coordinate: [0.59, 0.35]
        },
        {

            name: '许昌',
            coordinate: [0.53, 0.47]
        },
        {

            name: '平顶山',
            coordinate: [0.45, 0.54]
        },
        {

            name: '洛阳',
            coordinate: [0.36, 0.38]
        },
        {

            name: '周口',
            coordinate: [0.62, 0.55]
        },
        {

            name: '漯河',
            coordinate: [0.56, 0.56]
        },
        {

            name: '南阳',
            coordinate: [0.37, 0.66]
        },
        {

            name: '信阳',
            coordinate: [0.55, 0.81]
        },
        {

            name: '驻马店',
            coordinate: [0.55, 0.67]
        },
        {

            name: '济源',
            coordinate: [0.37, 0.29]
        },
        {

            name: '三门峡',
            coordinate: [0.20, 0.36]
        },
        {

            name: '商丘',
            coordinate: [0.76, 0.41]
        },
        {

            name: '鹤壁',
            coordinate: [0.59, 0.18]
        },
        {

            name: '濮阳',
            coordinate: [0.68, 0.17]
        },
        {

            name: '安阳',
            coordinate: [0.59, 0.10]
        }
    ],
    lines: [
        {

            source: '新乡',
            target: '郑州'
        },
        {

            source: '焦作',
            target: '郑州'
        },
        {

            source: '开封',
            target: '郑州'
        },
        {

            source: '许昌',
            target: '郑州'
        },
        {

            source: '平顶山',
            target: '郑州'
        },
        {

            source: '洛阳',
            target: '郑州'
        },
        {

            source: '周口',
            target: '郑州'
        },
        {

            source: '漯河',
            target: '郑州'
        },
        {

            source: '南阳',
            target: '郑州'
        },
        {

            source: '信阳',
            target: '郑州'
        },
        {

            source: '驻马店',
            target: '郑州'
        },
        {

            source: '济源',
            target: '郑州'
        },
        {

            source: '三门峡',
            target: '郑州'
        },
        {

            source: '商丘',
            target: '郑州'
        },
        {

            source: '鹤壁',
            target: '郑州'
        },
        {

            source: '濮阳',
            target: '郑州'
        },
        {

            source: '安阳',
            target: '郑州'
        }
    ],
    icon: {

        show: true,
        src: require('@/assets/map.png')
    },
    text: {

        show: true,
    },
    bgImgSrc: require('@/assets/map.png')
}

const config6={

    data: [
        {

            name: '周口',
            value: 55
        },
        {

            name: '南阳',
            value: 120
        },
        {

            name: '西峡',
            value: 71
        },
        {

            name: '驻马店',
            value: 66
        },
        {

            name: '新乡',
            value: 80
        },
        {

            name: '信阳',
            value: 35
        },
        {

            name: '漯河',
            value: 15
        }
    ],
    img: [
        require('@/assets/map.png'),
        require('@/assets/map.png'),
        require('@/assets/map.png'),
        require('@/assets/map.png'),
        require('@/assets/map.png'),
        require('@/assets/map.png'),
        require('@/assets/map.png')
    ],
    showValue: true
}

const config7={

    data: [
        {

            name: '洪鹤大桥',
            value: 167
        },
        {

            name: '甲子塘桥',
            value: 123
        },
        {

            name: '求水顶隧道',
            value: 55
        },
        {

            name: '观光路边城',
            value: 98
        },
    ],
    unit: '传感器数量个数',
    showValue: true
}

const option2 = {
    title: {
        text: '测量建筑种类',
        color: 'white'
    },
    color:['#D0212A','#21B041','#3821B5'],
    series: [
        {
            type: 'pie',
            data: [
                { name: '桥梁', value: 100 },
                { name: '边坡', value: 50 },
                { name: '隧道', value: 50 },
            ],
            insideLabel: {
                show: true
            }
        }
    ]
}

export {
    config1,config2,config3,config4,config5,config6,config7,option2}