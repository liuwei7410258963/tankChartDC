(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-bd45"],{IAxT:function(e,t,a){"use strict";var n=a("stDE");a.n(n).a},"Lt/l":function(e,t,a){"use strict";a.d(t,"a",function(){return i}),a.d(t,"c",function(){return r}),a.d(t,"b",function(){return l});var n=a("t3Un");function i(e){return Object(n.a)({url:"/delivery/confirm/list",method:"get",params:e})}function r(e){return Object(n.a)({url:"/delivery/confirm/update",method:"post",params:e})}function l(e){return Object(n.a)({url:"/inventoryTraceInTime",method:"get",params:e})}},"Vr/t":function(e,t,a){"use strict";var n=a("j+wA");a.n(n).a},bcjW:function(e,t,a){"use strict";a.r(t);var n=a("m1cH"),i=a.n(n),r=a("14Xm"),l=a.n(r),o=a("D3Ub"),s=a.n(o),c=a("wd/R"),u=a.n(c),d=a("XINx"),h=a("7Qib"),g=a("Lt/l"),m=a("Vt1q"),p=a("05G/"),f=a("hffR"),b={name:"DeliveryDefine",components:{elDragDialog:d.a,handleTable:f.a},data:function(){return{returnOilData:[],listLoading:!1,timeValue:[u()(new Date).format("YYYY-MM-DD 00:00:00"),u()(new Date).format("YYYY-MM-DD HH:mm:ss")],total:0,pickerOptions:{shortcuts:[{text:this.$t("table.today"),onClick:function(e){var t=new Date,a=new Date;a.setTime(new Date(a.toLocaleDateString()).getTime()),e.$emit("pick",[a,t])}},{text:this.$t("table.yesterday"),onClick:function(e){var t=new Date,a=new Date;a.setTime(a.getTime()-864e5),e.$emit("pick",[a,t])}},{text:this.$t("table.nearly_three_days"),onClick:function(e){var t=new Date,a=new Date;a.setTime(a.getTime()-2592e5),e.$emit("pick",[a,t])}},{text:this.$t("table.nearly_aweek"),onClick:function(e){var t=new Date,a=new Date;a.setTime(a.getTime()-6048e5),e.$emit("pick",[a,t])}}]},stationOptions:[],stationValue:"",pageSize:window.screen.height>768?[20,40,60,100]:[15,30,50,100],listQuery:{pageNum:1,pageRow:window.screen.height>768?20:15},dialogVisible:!1,dialogTitle:"确认回罐数据",checkRecords:[],tankno:"",updateId:"",tankOptions:[],returncans:"returnCans",height:window.screen.height>768?.6*window.screen.height:.38*window.screen.height,tableHeader:this.getHeader(),tableOption:{border:!0,height:.6*window.screen.height},updateCheck:(Object(h.e)("payOil_checked").replace(/\"/g,"")||this.getChecked().toString()).split(","),updateHeader:Object(h.f)("payOil",this.getHeader())||this.getHeader(),detailData:[],returnVolume:0,selectFlag:!0,checkSelect:[]}},created:function(){this.getAllReturnOilData()},mounted:function(){},methods:{handleSizeChange:function(e){this.listQuery.pageRow=e,this.listQuery.pageNum=1,this.getAllReturnOilData()},handleCurrentChange:function(e){this.listQuery.pageNum=e,this.getAllReturnOilData()},getAllReturnOilData:function(){var e=this;s()(l.a.mark(function t(){return l.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,e.listLoading=!0,e.listQueryCheck(),t.next=5,Object(m.f)({status:"1"});case 5:return e.tankOptions=t.sent,e.returnOilData=[{id:1,tankNo:"1",gunCode:"1",volume:"300",startTime:"2020-04-01 00:00:00",endTime:"2020-04-30 00:00:00",flag:!1}],t.next=9,Object(g.a)(e.listQuery);case 9:t.sent,e.listLoading=!1,t.next=17;break;case 14:t.prev=14,t.t0=t.catch(0);case 17:case"end":return t.stop()}},t,e,[[0,14]])}))()},handleSelectionChange:function(e){this.checkRecords=[].concat(i()(e))},confirm:function(){this.updateId="",this.submit(!0)},statusChange:function(e){var t=this;this.returnVolume=e.volume,this.dialogTitle="确认回罐数据",this.dialogVisible=!0,this.selectFlag=!0,this.$nextTick(function(){t.getRelateOilData(e)})},getRelateOilData:function(e){var t=this;s()(l.a.mark(function a(){var n,i,r,o,s,c,u,d;return l.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.prev=0,t.detailData=[{oilVolume:"300",startTime:"2020-04-01 00:00:00",endTime:"2020-04-01 03:00:00"},{oilVolume:"500",startTime:"2020-04-01 00:00:00",endTime:"2020-04-01 03:00:00"}],n=new Date(e.startTime.replace(/-/g,"/")),i=n.getTime(),r=i-18e4,o=Object(h.g)(r),s=new Date(e.endTime.replace(/-/g,"/")),c=s.getTime(),u=c+18e4,d=Object(h.g)(u),a.next=12,Object(p.a)({startTime:o,endTime:d,flag:!1});case 12:a.sent,a.next=19;break;case 16:a.prev=16,a.t0=a.catch(0);case 19:case"end":return a.stop()}},a,t,[[0,16]])}))()},selectChange:function(e){this.checkSelect=[].concat(i()(e))},selectableFun:function(e,t){return this.checkSelect.length?this.checkSelect[0]===e:this.selectFlag},submit:function(e){var t=this,a={flag:e,ids:this.updateId||this.checkRecords.join(",")};Object(g.c)(a).then(function(e){"success"===e.info.result?t.$message.success("修改成功"):t.$message.success("修改失败"),t.dialogVisible=!1,t.getAllReturnOilData()})},listQueryCheck:function(){this.listQuery.tankNo=this.tankno||null,this.listQuery.startTime=this.timeValue?Object(h.g)(this.timeValue[0]):null,this.listQuery.endTime=this.timeValue?Object(h.g)(this.timeValue[1]):null},getHeader:function(){return[{prop:"tankNo",label:this.$t("alarmParam.tank_code")},{prop:"gunCode",label:"油枪号"},{prop:"volume",label:"回罐量"},{prop:"volume",label:"开始液位"},{prop:"volume",label:"结束液位"},{prop:"volume",label:"开始体积"},{prop:"volume",label:"结束体积"},{prop:"startTime",label:this.$t("table.startTime"),width:180},{prop:"endTime",label:this.$t("table.endTime"),width:180},{prop:"volume",label:"关联付油量"},{prop:"startTime",label:"关联开始时间",width:180},{prop:"endTime",label:"关联结束时间",width:180}]},getChecked:function(){var e=[];for(var t in this.getHeader())e.push(this.getHeader()[t].label);return e}}},v=(a("Vr/t"),a("KHd+")),w=Object(v.a)(b,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"container"}},[a("div",{attrs:{id:"top"}},[a("span",{staticStyle:{"font-size":"16px"}},[e._v("罐号")]),e._v(" "),a("el-select",{attrs:{clearable:""},model:{value:e.tankno,callback:function(t){e.tankno=t},expression:"tankno"}},e._l(e.tankOptions,function(e){return a("el-option",{key:e.tankNo,attrs:{label:e.detailInfo,value:e.tankNo}})})),e._v(" "),a("span",{staticStyle:{"font-size":"16px"}},[e._v(e._s(e.$t("table.time"))+":")]),e._v(" "),a("el-date-picker",{attrs:{"start-placeholder":e.$t("table.startTime"),"end-placeholder":e.$t("table.endTime"),"picker-options":e.pickerOptions,type:"datetimerange",align:"right"},model:{value:e.timeValue,callback:function(t){e.timeValue=t},expression:"timeValue"}}),e._v(" "),a("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(t){e.listQuery.pageNum=1,e.getAllReturnOilData()}}},[e._v("搜索")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.confirm}},[e._v("确认")])],1),e._v(" "),a("handleTable",{ref:"returncans",staticClass:"out-table",staticStyle:{width:"100%"},attrs:{loading:e.listLoading,data:e.returnOilData,header:e.tableHeader,option:e.tableOption,height:e.height,defaultcheck:e.updateCheck,defaulheader:e.updateHeader,vuename:e.returncans,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""},on:{onHandleSelectionChange:e.handleSelectionChange}},[a("el-table-column",{attrs:{slot:"fixed",align:"center",type:"selection",width:"60"},slot:"fixed"}),e._v(" "),a("el-table-column",{attrs:{slot:"fixed",label:"状态",align:"left"},slot:"fixed",scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.flag?a("el-button",{attrs:{type:"success",size:"mini"},on:{click:function(a){e.statusChange(t.row)}}},[e._v("已确认")]):a("el-button",{attrs:{type:"danger",size:"mini"},on:{click:function(a){e.statusChange(t.row)}}},[e._v("未确认")])]}}])})],1),e._v(" "),a("el-dialog",{directives:[{name:"el-drag-dialog",rawName:"v-el-drag-dialog"}],attrs:{visible:e.dialogVisible,title:e.dialogTitle,width:"951px"},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("span",{staticStyle:{"font-size":"16px"}},[e._v("回罐量： "+e._s(e.returnVolume)+" L")]),e._v(" "),a("el-table",{staticStyle:{"margin-top":"10px"},attrs:{data:e.detailData,border:"",height:"400px"},on:{"selection-change":e.selectChange}},[a("el-table-column",{attrs:{selectable:e.selectableFun,type:"selection",width:"50"}}),e._v(" "),a("el-table-column",{attrs:{prop:"oilVolume",label:"付油量(L)"}}),e._v(" "),a("el-table-column",{attrs:{prop:"startTime",label:"开始时间"}}),e._v(" "),a("el-table-column",{attrs:{prop:"endTime",label:"结束时间"}})],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.submit(!1)}}},[e._v("取消确认")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.submit(!0)}}},[e._v("确 定")])],1)],1),e._v(" "),a("div",{staticClass:"block"},[a("el-pagination",{attrs:{"current-page":e.listQuery.pageNum,"page-size":e.listQuery.pageRow,total:e.total,"page-sizes":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",background:""},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1)},[],!1,null,"a645d536",null);w.options.__file="return_cans.vue";t.default=w.exports},hffR:function(e,t,a){"use strict";var n=a("gDS+"),i=a.n(n),r={props:{data:{default:function(){return[]},type:Array},header:{default:function(){return[]},type:Array},option:{default:function(){return{}},type:Object},defaultcheck:{default:function(){return[]},type:Array},defaulheader:{default:function(){return[]},type:Array},vuename:{default:function(){return""},type:String},callback:{type:Function,default:null},loading:{type:Boolean,default:!1},height:{default:function(){return window.screen.height>768?.595*window.screen.height:.64*window.screen.height},type:Number}},data:function(){return{tableHeader:this.header,dragState:{start:-9,end:-9,dragging:!1,direction:void 0},checkboxVal:this.defaultcheck,formThead:this.defaulheader,check_width:this.header.length>20?400:220}},watch:{checkboxVal:function(e,t){this.formThead=this.tableHeader.filter(function(t){return e.indexOf(t.label)>=0}),this.setCookie(this.vuename,i()(this.formThead)),this.setCookie(this.vuename+"_checked",'"'+e.toString()+'"'),this.key=this.key+1}},methods:{callbackClassName:function(e){var t=e.row,a=e.rowIndex;if(this.callback)return this.callback({row:t,rowIndex:a})},findHidden:function(){this.defaulheader.forEach(function(e){})},handleSelectionChange:function(e){this.$emit("onHandleSelectionChange",e)},rowClick:function(e,t,a){if(a&&"selection"!==a.type&&"operation"!==a.columnKey&&"expand"!==a.type){var n={row:e,event:t,column:a};this.$emit("onRowClick",n)}},renderHeader:function(e,t){var a=this,n=t.column;return e("div",{class:["thead-cell"],on:{mousedown:function(e){a.handleMouseDown(e,n)},mousemove:function(e){a.handleMouseMove(e,n)}}},[e("a",n.label),e("span",{class:["virtual"]})])},handleMouseDown:function(e,t){this.dragState.dragging=!0,this.dragState.start=parseInt(t.columnKey),document.addEventListener("mouseup",this.handleMouseUp)},handleMouseUp:function(){this.dragColumn(this.dragState),this.dragState={start:-9,end:-9,dragging:!1,direction:void 0},document.removeEventListener("mouseup",this.handleMouseUp)},handleMouseMove:function(e,t){if(!this.dragState.dragging)return!1;var a=parseInt(t.columnKey);a-this.dragState.start!=0?(this.dragState.direction=a-this.dragState.start<0?"left":"right",this.dragState.end=parseInt(t.columnKey)):this.dragState.direction=void 0},dragColumn:function(e){for(var t=e.start,a=e.end,n=[],r="left"===e.direction,l=r?a:t-1,o=r?t+1:a,s=0;s<this.formThead.length;s++)s===a?n.push(this.formThead[t]):s>l&&s<o?n.push(this.formThead[r?s-1:s+1]):n.push(this.formThead[s]);this.formThead=n,this.setCookie(this.vuename,i()(this.formThead))},headerCellClassName:function(e){e.column;var t=e.columnIndex;return(t===this.dragState.end?"darg_active_"+this.dragState.direction:"")+" "+(t===this.dragState.start?"darg_start":"")},cellClassName:function(e){e.column;return e.columnIndex===this.dragState.start?"darg_start":""},tableRowClassName:function(e){var t=e.row,a=e.rowIndex;this.$emit("tableRowClassName",{row:t,rowIndex:a})},setCookie:function(e,t){document.cookie=e+"="+t+"; "}}},l=(a("IAxT"),a("KHd+")),o=Object(l.a)(r,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"w-table",class:{"w-table_moving":e.dragState.dragging}},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],style:{width:parseInt(e.option.width)+"px"},attrs:{data:e.data,border:e.option.border,height:e.height,"max-height":e.option.maxHeight,"cell-class-name":e.cellClassName,"row-class-name":e.callbackClassName,"header-cell-class-name":e.headerCellClassName,"highlight-current-row":"",stripe:""},on:{"selection-change":e.handleSelectionChange,"row-click":e.rowClick}},[e._t("fixed"),e._v(" "),e._l(e.formThead,function(t,n){return a("el-table-column",{key:n,attrs:{prop:t.prop,label:e.$t(t.label),width:t.width,"min-width":t.minWidth,type:t.type,fixed:t.fixed,formatter:t.formatter,"header-align":t.headerAlign,"column-key":n.toString(),"render-header":e.renderHeader,align:"center","show-overflow-tooltip":""}})}),e._v(" "),e._t("handled")],2)],1)},[],!1,null,null,null);o.options.__file="handleTable.vue";t.a=o.exports},"j+wA":function(e,t,a){},stDE:function(e,t,a){}}]);