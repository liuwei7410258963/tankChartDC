(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-6c05"],{"14sS":function(t,e,n){t.exports=n.p+"static/img/onlineOilgun.74534bc.png"},FfD9:function(t,e,n){"use strict";var a=n("pF8z");n.n(a).a},YlR1:function(t,e,n){t.exports=n.p+"static/img/oilingGun.2ec791d.png"},ewQf:function(t,e,n){"use strict";n.r(e);var a=n("ytOI"),r=n.n(a),c={data:function(){return{oilGunData:[],stationName:"",imgSrc:r.a}},methods:{dealOilGunImg:function(t){return n(1===t?"YlR1":2===t?"hHoB":"14sS")},getOption:function(t,e){t&&t.forEach(function(t){e&&e.forEach(function(e){if(t.nozzleNo+""==e.nozzleNo+""){switch(e.currentState){case"INOPERATIVE":t.currentState="不允许操作",t.stateMark=1;break;case"CLOSE":t.currentState="关闭",t.stateMark=2;break;case"IDLE":t.currentState="空闲",t.stateMark=0;break;case"CALLING":t.currentState="请求授权",t.stateMark=0;break;case"AUTHORIZED":t.currentState="已授权",t.stateMark=0;break;case"STARTED":t.currentState="开始加油",t.stateMark=0;break;case"FUELLING":t.currentState="加油中",t.stateMark=0;break;case"SUSPENDED_STARTED":t.currentState="暂停开始加油",t.stateMark=0;break;case"SUSPENDED_FUELLING":t.currentState="暂停加油中",t.stateMark=0}t.currentTime=e.currentTime,t.currentVolume=e.currentVolume}})}),t&&(this.oilGunData=t.sort(this.sortByCode))},sortByCode:function(t,e){return t.nozzleNo-e.nozzleNo}}},s=(n("FfD9"),n("KHd+")),i=Object(s.a)(c,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"container"}},[n("el-row",{staticStyle:{clear:"both"},attrs:{gutter:10}},t._l(t.oilGunData,function(e,a){return n("el-col",{key:a,staticStyle:{"margin-bottom":"10px"},attrs:{xl:6,lg:8,md:12}},[n("el-card",{staticClass:"box-card",staticStyle:{height:"100%"}},[n("table",{staticStyle:{width:"95%"},attrs:{cellspacing:"0"}},[n("tr",[n("td",{attrs:{colspan:"2"}},[t._v("\n              油枪:"+t._s(e.nozzleNo)+"\n            ")]),t._v(" "),n("td",{staticStyle:{background:"rgba(196,223,184,0.2)"},attrs:{colspan:"2"}},[t._v("\n              油枪状态\n            ")])]),t._v(" "),n("tr",[n("td",{attrs:{colspan:"2"}},[t._v("\n              "+t._s(e.oilName)+"\n            ")]),t._v(" "),n("td",[t._v("\n              更新时间\n            ")]),t._v(" "),n("td",[t._v("\n              "+t._s(e.currentTime||"未更新")+"\n            ")])]),t._v(" "),n("tr",[n("td",{staticClass:"liguidSty",attrs:{rowspan:"9",colspan:"2"}},[n("div",{staticStyle:{width:"100%",display:"flex","flex-flow":"column wrap","text-align":"center"}},[n("img",{staticStyle:{margin:"0 auto"},attrs:{src:t.dealOilGunImg(e.stateMark),width:"80px"}})])])]),t._v(" "),n("tr",[n("td",[t._v("\n              状态\n            ")]),t._v(" "),n("td",[t._v("\n              "+t._s(e.currentState)+"\n            ")])]),t._v(" "),n("tr",[n("td",[t._v("\n              实时付出数\n            ")]),t._v(" "),n("td",[t._v("\n              "+t._s(e.currentVolume)+" L\n            ")])]),t._v(" "),n("tr",[n("td",{staticStyle:{background:"rgba(59,154,255,0.1)"},attrs:{colspan:"2"}},[t._v("\n              最近一笔付油\n            ")])]),t._v(" "),n("tr",[n("td",[t._v("\n              付油量\n            ")]),t._v(" "),n("td",[t._v("\n              "+t._s(e.volume)+" L\n            ")])]),t._v(" "),n("tr",[n("td",[t._v("\n              "+t._s(t.$t("table.endTime"))+"\n            ")]),t._v(" "),n("td",[t._v("\n              "+t._s(e.endTime)+"\n            ")])]),t._v(" "),n("tr",[n("td",[t._v("\n              累计泵码\n            ")]),t._v(" "),n("td",[t._v("\n              "+t._s(e.pumpSum)+"\n            ")])])])])],1)}))],1)},[],!1,null,"2dd3a411",null);i.options.__file="nozzle.vue";e.default=i.exports},hHoB:function(t,e,n){t.exports=n.p+"static/img/offlineOilGun.f4f10bd.png"},pF8z:function(t,e,n){},ytOI:function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAEDklEQVRYR+2WfWxTVRTAz3197fqB7WpX6ihiIxHWztls6wQZBSfiOqMxGWpUFBaj7C0xMTEKUf8yxj8kxJAArlHImmEUGkDJjOuCm7BBAsxJukEn27IvKPLWzdHRrt/vmveW1demvq3ZEGO4/9x73znnnt8759wPBHe5obvsH/5jAMdqnUAgLT8q12UtaxEAxgBoRGUL50l1UoQQxhij1cM3z2cdQQynYYv9k1m71AgcpzB/wTrxEHwp6eY+9YoMsPyBZ5PitSNj4InEBP3XkKPgiK9I1UFwGqrtFXMCSIGBQfkpyEdhTveX3FfBolBz4wOTAfjQ5xd0rkdhOCvrgPUhK3ix9G/d+QJ8JumFj8R9nOF5iRkKteXc2M8wUDREw60EIwjwucQDO8X9sDv2COyKmrIDWEkEYUD2c9LooqYGjFI5N//Y54d9k4E5U0+Rw1Cf44a6iBnscUN2AI6c32A7eY0zapNaoUxTxI1/j8bg8eGxOZ0LKgim4Bj17XPkTeM7kqFl7CIIRAhrniFCmEyw8x+DofFD/uCcBI86B2eIeW1qa0nfaOxWBAAdhep6e+YiXNi/8a1TdhMrcLvdD5rN5uvpLu7IQVRLUXGCIER8Z1Rt7b8H4O7pYfduTsrfMsw9gOwjUGK1fcHeAxlrEwHT1e76IJNsUVJQWLaxTCqTXRTaGCgBBb+ec11N11k4QBOll+1qvmrKMyqoN9+AXJUyxQftG4eGb44C6u8OdG5+ugA1Nnr5CgsGEDe+XU/uaaVMuauABXhr+2spAAe+dnAAikEPHF+3zq51HqlbVADpepMP+YIKk84oKyo0ZsxCz5VewP3dYYNiSeDEQF/KG6K0tDSBCILgGx46eHD+RVhQ/AQtVypfRBi1C9YAxmvC4dDJy53t+aye09mkj0K0czo4zc35TS5XTAHGla+/siXlEZPxJCzdYMO0JXfHjTWqrzYuXcWtc47uA/8PfiBMM1cr4/HA/qnQ1iOByKdd7a6V7LeG7743iAlmSKfVAO2bgLz71TD+52SyZxDatO3l6jY+WEaAkg22k/m2Iu+VYkldg2Ubp195Zi9ELiwBqKmZsXc4YH8gtMdxuV/V1dGygw8gEhGQSDCQ3s8boNhaZdIUr2jufEqWfE+RGIBumkoJ6/vTiYEz3rEXLnU0e/gA/5S2eQOwC+zeu++lyk1POoVqoOVUW9XO9951zerMpmBRAcZ8E9x6S7UarufP/z8AlgrbapxAz/NDl6/Le2y5flmFmCS5YzAWj3MFwJ8Pj15z+cYnk0exUnWf+iG9vlytVj6cnobbtwNe2jfhvvEHPcDKSHH08IXWVprbBRZrVTlG+KxQvgEQLSxPl2KdkD5DEoZLbT+N3JEXUTag9wD+AuOP3zDkrZm/AAAAAElFTkSuQmCC"}}]);