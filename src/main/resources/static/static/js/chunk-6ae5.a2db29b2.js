(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-6ae5"],{k4c0:function(t,e,n){"use strict";n.r(e);var a=n("MQ60"),s={name:"DragKanbanDemo",components:{draggable:n.n(a).a},props:{headerText:{type:String,default:"Header"},options:{type:Object,default:function(){return{}}},list:{type:Array,default:function(){return[]}},returnInfo:{type:Object,default:function(){return{}}}},data:function(){return{timeDes:30}},methods:{setData:function(t){t.setData("Text","")},dragListChange:function(t){this.$emit("onChange",t)},getHostoutByTime:function(){this.$emit("onChange",Number(this.timeDes))},resetTime:function(){this.timeDes=30}}},i=(n("noBA"),n("KHd+")),o=Object(i.a)(s,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"board-column"},["1"===t.headerText?n("div",{staticClass:"board-column-header",staticStyle:{margin:"0 auto"}},[t._v("\n    关联付油数据\n  ")]):n("div",{staticClass:"board-column-header",staticStyle:{margin:"0 auto"}},[t._v("\n    附近付油数据\n    "),n("div",{staticStyle:{float:"right"}},[t._v("\n      前\n      "),n("el-input",{staticStyle:{display:"inline-block",width:"60px"},attrs:{size:"mini"},model:{value:t.timeDes,callback:function(e){t.timeDes=e},expression:"timeDes"}}),t._v("\n      分钟付油数据\n      "),n("el-button",{attrs:{plain:"",size:"mini"},on:{click:t.getHostoutByTime}},[t._v("\n        查询\n      ")])],1)]),t._v(" "),n("draggable",t._b({staticClass:"board-column-content",attrs:{list:t.list,"set-data":t.setData},on:{change:t.dragListChange}},"draggable",t.$attrs,!1),t._l(t.list,function(e,a){return n("div",{key:a,staticClass:"board-item",style:e.from?"background: #fff":"background: #f9f9a9"},[n("p",{staticClass:"p_base"},[t._v("\n        油枪编号:"+t._s(e.nozzleNo||e.nozzle_no)+"\n      ")]),t._v(" "),n("p",{staticClass:"p_base"},[t._v("\n        开始时间:"+t._s(e.startTime||e.backConfirmStartTime)+"\n      ")]),t._v(" "),n("p",{staticClass:"p_base"},[t._v("\n        结束时间:"+t._s(e.endTime||e.backConfirmEndTime)+"\n      ")]),t._v(" "),n("p",{staticClass:"p_base"},[t._v("\n        付油量:"+t._s(e.volume?e.volume:0)+"\n      ")])])}))],1)},[],!1,null,"0bb12b81",null);o.options.__file="index.vue";e.default=o.exports},lM4G:function(t,e,n){},noBA:function(t,e,n){"use strict";var a=n("lM4G");n.n(a).a}}]);