(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-574c"],{"8Lhh":function(t,e,n){"use strict";var a=n("WENq");n.n(a).a},TxWJ:function(t,e,n){},WENq:function(t,e,n){},bRJQ:function(t,e,n){"use strict";var a=n("TxWJ");n.n(a).a},f8Ow:function(t,e,n){"use strict";n.r(e);var a=n("rG1V"),r=n("X4fA"),o={name:"TotalTable",props:{},data:function(){return{pageNum:1,tableHeight:innerHeight-310,tableData:[],flag:!1,total:0,username:"admin"}},mounted:function(){this.username=Object(r.b)()},methods:{getTankNo:function(t,e){var n=this;this.pageNum=t,this.tankNo=e;var r={tankNo:this.tankNo,pageNum:this.pageNum};Object(a.c)(r).then(function(t){n.tableData=t.info.list,n.tableData.forEach(function(t,e){t.index=e}),n.total=t.info.totalCount,n.flag=n.pageNum<t.info.totalPage})},prePage:function(){this.getTankNo(--this.pageNum,this.tankNo)},nextPage:function(){this.getTankNo(++this.pageNum,this.tankNo)},tableRowClassName:function(t){var e=t.row,n=t.rowIndex;return e.flag?"flag-row":n%2?"another":""},merge:function(t,e){var n=this,r=e.groupId,o=e.traceStartTime,i=e.traceEndTime,l=e.startId,c=e.endId,s=0;if(t){if(this.tableData[e.index-1].flag)return void this.$message.warning("不能与该数据合并");r+=","+this.tableData[e.index-1].groupId,i=this.tableData[e.index-1].traceEndTime,c=this.tableData[e.index-1].endId,s=e.index}else{if(this.tableData[e.index+1].flag)return void this.$message.warning("不能与该数据合并");r+=","+this.tableData[e.index+1].groupId,o=this.tableData[e.index+1].traceStartTime,l=this.tableData[e.index+1].startId,s=e.index+2}var u={tankNo:e.tankNo,startTime:o,endTime:i,segments:[{startId:l,endId:c,levelState:e.traceLevelstate}],groupIds:r};this.$confirm("是否确定合并序号"+(e.index+1)+"和序号"+s+"的数据?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(a.e)(u).then(function(t){"100"===t.code&&(n.$message({type:"success",message:"修改成功"}),n.getTankNo(1,n.tankNo))})}).catch(function(){n.$message({type:"info",message:"已取消"})})}}},i=(n("8Lhh"),n("bRJQ"),n("KHd+")),l=Object(i.a)(o,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"container"}},[n("span",{staticClass:"tipsStyle"},[t._v("\n    【提示】体积单位:L  高度单位:mm  温度:℃\n  ")]),t._v(" "),n("el-table",{staticStyle:{width:"100%"},attrs:{"row-class-name":t.tableRowClassName,data:t.tableData,height:t.tableHeight,border:""}},["admin"===t.username?n("el-table-column",{attrs:{label:"操作",align:"center",width:"70"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-popover",{attrs:{id:"popover",placement:"right",trigger:"click"}},[n("div",[e.row.index>0?n("el-button",{attrs:{type:"success",size:"mini",plain:"",icon:"el-icon-upload2"},on:{click:function(n){t.merge(!0,e.row)}}},[t._v("\n              与上组合并\n            ")]):t._e(),t._v(" "),e.row.index<t.tableData.length-1?n("el-button",{attrs:{type:"warning",size:"mini",plain:"",icon:"el-icon-download"},on:{click:function(n){t.merge(!1,e.row)}}},[t._v("\n              与下组合并\n            ")]):t._e()],1),t._v(" "),n("el-button",{attrs:{slot:"reference",disabled:e.row.flag,type:"text",size:"mini"},slot:"reference"},[t._v("\n            合并\n          ")])],1)]}}])}):t._e(),t._v(" "),n("el-table-column",{attrs:{prop:"level",align:"center","min-width":"70",label:"高度"}}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"95"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"付出数 - 相对体积差(Vout - (Vt_out[i] - Vt_out[i-1]))",placement:"top"}},[n("span",[t._v("段内误差")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.segmentError?e.row.segmentError.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{prop:"temp",align:"center","min-width":"70",label:"温度"}}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"70"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"和上一笔液位之间付出数(Vout)",placement:"top"}},[n("span",[t._v("付出数")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.outNum?e.row.outNum.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"100"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"付出数总和(Sum(Vout))",placement:"top"}},[n("span",[t._v("付出数合计")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.outSum?e.row.outSum.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"90"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"液位高度对应的相对体积V(t_out)",placement:"top"}},[n("span",[t._v("相对体积")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.volume?e.row.volume.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"100"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"与上一条数据的相对体积差值(Vt_out[i] - Vt_out[i-1])",placement:"top"}},[n("span",[t._v("相对体积差")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.volumeDifference?e.row.volumeDifference.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"70"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"付出数合计 - 相对体积(Vout - V(t_out))",placement:"top"}},[n("span",[t._v("误差")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.error?e.row.error.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"70"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"误差 / 相对体积，用千分率表示(Vout-V(t_out))/V(t_out)",placement:"top"}},[n("span",[t._v("误差率")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.errorRate?e.row.errorRate.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"125"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"相对体积差/高度差（与上一条高度的差值）((Vt_out[i] - Vt_out[i-1]) / △h)",placement:"top"}},[n("span",[t._v("段内mm变化率")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.segmentRate?e.row.segmentRate.toFixed(2):0)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center","min-width":"70"},scopedSlots:t._u([{key:"header",fn:function(e){return[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"对应的周期的id",placement:"top"}},[n("span",[t._v("周期")])])]}},{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.deliveryId)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{type:"index",label:"序号",width:"70",align:"center"}})],1),t._v(" "),n("div",{staticStyle:{"margin-top":"10px"}},[t.flag?n("el-button",{staticStyle:{float:"right","margin-left":"10px"},attrs:{type:"primary"},on:{click:t.nextPage}},[t._v("\n      下一页\n    ")]):t._e(),t._v(" "),t.pageNum>1?n("el-button",{staticStyle:{float:"right"},attrs:{type:"primary"},on:{click:t.prePage}},[t._v("\n      上一页\n    ")]):t._e()],1)],1)},[],!1,null,"b7cf3eea",null);l.options.__file="index.vue";e.default=l.exports},rG1V:function(t,e,n){"use strict";n.d(e,"b",function(){return r}),n.d(e,"c",function(){return o}),n.d(e,"e",function(){return i}),n.d(e,"a",function(){return l}),n.d(e,"d",function(){return c}),n.d(e,"f",function(){return s});var a=n("t3Un");function r(t){return Object(a.a)({url:"/correcting/select",method:"get",params:t})}function o(t){return Object(a.a)({url:"/correcting/selecttable",method:"get",params:t})}function i(t){return Object(a.a)({url:"/correcting/update",method:"post",data:t})}function l(t){return Object(a.a)({url:"/tankchartdc/export/export",method:"post",data:t})}function c(t){return Object(a.a)({url:"/tankchartdc/inventoryCycle/select",method:"get",params:t})}function s(t){return Object(a.a)({url:"/tankchartdc/export/select",method:"get",params:t})}}}]);