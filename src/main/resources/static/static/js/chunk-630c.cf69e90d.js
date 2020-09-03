(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-630c"],{"+z3n":function(t,e,n){},"4ouI":function(t,e,n){"use strict";var a=n("+z3n");n.n(a).a},"8Lhh":function(t,e,n){"use strict";var a=n("WENq");n.n(a).a},DKM7:function(t,e,n){"use strict";var a=n("dzWe");n.n(a).a},WENq:function(t,e,n){},dzWe:function(t,e,n){},f8Ow:function(t,e,n){"use strict";n.r(e);var a=n("rG1V"),o=n("X4fA"),r={name:"TotalTable",components:{},props:{},data:function(){return{pageNum:1,tableHeight:innerHeight-310,tableData:[],flag:!1,total:0,username:"admin"}},mounted:function(){this.username=Object(o.b)()},methods:{getTankNo:function(t,e){var n=this;this.pageNum=t,this.tankNo=e;var o={tankNo:this.tankNo,pageNum:this.pageNum};Object(a.d)(o).then(function(t){n.tableData=t.info.list,n.tableData.forEach(function(t,e){t.index=e}),n.total=t.info.totalCount,n.flag=n.pageNum<t.info.totalPage})},tableRowClassName:function(t){var e=t.row,n=t.rowIndex;return e.flag?"flag-row":n%2?"another":""},toDetail:function(t){var e={startTime:t.startInventory.time,endTime:t.endInventory.time,tankNo:t.tankNo};this.$emit("openDetail",e)},prePage:function(){this.getTankNo(--this.pageNum,this.tankNo)},nextPage:function(){this.getTankNo(++this.pageNum,this.tankNo)},colse:function(){}}},i=(n("8Lhh"),n("DKM7"),n("4ouI"),n("KHd+")),l=Object(i.a)(r,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"container"}},[n("span",{staticClass:"tipsStyle"},[t._v("\n    【提示】体积单位:L  高度单位:mm  温度:℃\n  ")]),t._v(" "),n("el-table",{staticStyle:{width:"100%"},attrs:{id:"table","row-class-name":t.tableRowClassName,data:t.tableData,height:t.tableHeight,border:""}},["admin"===t.username?n("el-table-column",{attrs:{label:"查看详情",align:"center",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-button",{attrs:{disabled:e.row.flag,type:"text",size:"mini"},on:{click:function(n){t.toDetail(e.row)}}},[t._v("\n          查看\n        ")])]}}])}):t._e(),t._v(" "),n("el-table-column",{attrs:{prop:"level",align:"center","min-width":"70",label:"高度"}}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"95"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"付出数 - 相对体积差(Vout - (Vt_out[i] - Vt_out[i-1]))",placement:"top"}},[n("span",[t._v("段内误差")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.segmentError?e.row.segmentError.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{prop:"temp",align:"center","min-width":"70",label:"温度"}}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"70"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"和上一笔液位之间付出数(Vout)",placement:"top"}},[n("span",[t._v("付出数")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.outNum?e.row.outNum.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"100"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"付出数总和(Sum(Vout))",placement:"top"}},[n("span",[t._v("付出数合计")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.outSum?e.row.outSum.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"90"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"液位高度对应的相对体积V(t_out)",placement:"top"}},[n("span",[t._v("相对体积")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.volume?e.row.volume.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"100"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"与上一条数据的相对体积差值(Vt_out[i] - Vt_out[i-1])",placement:"top"}},[n("span",[t._v("相对体积差")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.volumeDifference?e.row.volumeDifference.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"70"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"付出数合计 - 相对体积(Vout - V(t_out))",placement:"top"}},[n("span",[t._v("误差")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.error?e.row.error.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"95"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"误差 / 相对体积，用千分率表示(Vout-V(t_out))/V(t_out)",placement:"top"}},[n("span",[t._v("误差率 (‰)")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.errorRate?e.row.errorRate.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"125"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"相对体积差/高度差（与上一条高度的差值）((Vt_out[i] - Vt_out[i-1]) / △h)",placement:"top"}},[n("span",[t._v("段内mm变化率")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.segmentRate?e.row.segmentRate.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"70"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"对应的周期的id",placement:"top"}},[n("span",[t._v("周期")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.deliveryId)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{type:"index",label:"序号",width:"70",align:"center"}})],1),t._v(" "),n("div",{staticStyle:{"margin-top":"10px"}},[t.flag?n("el-button",{staticStyle:{float:"right","margin-left":"10px"},attrs:{type:"primary"},on:{click:t.nextPage}},[t._v("\n      下一页\n    ")]):t._e(),t._v(" "),t.pageNum>1?n("el-button",{staticStyle:{float:"right"},attrs:{type:"primary"},on:{click:t.prePage}},[t._v("\n      上一页\n    ")]):t._e()],1)],1)},[],!1,null,"95bccc44",null);l.options.__file="index.vue";e.default=l.exports},rG1V:function(t,e,n){"use strict";n.d(e,"c",function(){return o}),n.d(e,"d",function(){return r}),n.d(e,"a",function(){return i}),n.d(e,"e",function(){return l}),n.d(e,"g",function(){return c}),n.d(e,"f",function(){return u}),n.d(e,"b",function(){return s});var a=n("t3Un");function o(t){return Object(a.a)({url:"/correcting/select",method:"get",params:t})}function r(t){return Object(a.a)({url:"/correcting/selecttable",method:"get",params:t})}function i(t){return Object(a.a)({url:"/tankchartdc/export/export",method:"post",data:t})}function l(t){return Object(a.a)({url:"/tankchartdc/inventoryCycle/select",method:"get",params:t})}function c(t){return Object(a.a)({url:"/tankchartdc/export/select",method:"get",params:t})}function u(t){return Object(a.a)({url:"/correcting/selectdelivery",method:"get",params:t})}function s(t){return Object(a.a)({url:"/correcting/generate",method:"get",params:t})}}}]);