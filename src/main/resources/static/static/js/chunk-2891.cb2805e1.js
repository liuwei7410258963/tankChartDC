(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-2891"],{"1C6H":function(e,t,n){},HtDl:function(e,t,n){"use strict";n.r(t);var a=n("wd/R"),i=n.n(a),r=n("XINx"),s=n("Rut5"),l=n("7Qib"),o=n("h06r"),u=n("Vt1q"),c=n("hffR"),d=n("fW9N"),h={name:"ReturnCansPage",components:{elDragDialog:r.a,handleTable:c.a,ReturnCans:d.default,DatePicker:s.a},data:function(){return{returnOilData:[],listLoading:!1,timeValue:[i()(new Date).format("YYYY/MM/DD 00:00:00"),i()(new Date).format("YYYY/MM/DD 23:59:59")],total:0,stationOptions:[],stationValue:"",pageSize:window.screen.height>768?[20,40,60,100]:[15,30,50,100],listQuery:{pageNum:1,pageRow:window.screen.height>768?20:15,flag:!0},dialogVisible:!1,dialogTitle:"确认回罐数据",checkRecords:[],tankno:"",updateId:"",tankOptions:[],type:"",typeOptions:[{id:"",label:"全部"},{id:1,label:"DIT判断"},{id:2,label:"系统判断"}],returncans:"returnCans",height:window.screen.height>768?window.screen.height-405:window.screen.height-375,tableHeader:this.getHeader(),tableOption:{border:!0,height:.6*window.screen.height},updateCheck:(Object(l.e)("returnCans_checked").replace(/\"/g,"")||this.getChecked().toString()).split(","),updateHeader:Object(l.f)("returnCans",this.getHeader())||this.getHeader(),detailData:[],returnVolume:0,selectFlag:!0,checkSelect:[],gridData:[]}},created:function(){this.getTank()},activated:function(){this.getAllReturnOilData()},methods:{handleSizeChange:function(e){this.listQuery.pageRow=e,this.listQuery.pageNum=1,this.getAllReturnOilData()},handleCurrentChange:function(e){this.listQuery.pageNum=e,this.getAllReturnOilData()},getTank:function(){var e=this;Object(u.e)({status:"1"}).then(function(t){e.tankOptions=t})},getAllReturnOilData:function(){var e=this;this.$route.params.name="",this.listLoading=!0,this.listQueryCheck(),this.listQuery.tankNo=this.tankno||null,this.listQuery.startTime=this.timeValue.length?Object(l.g)(this.timeValue[0]):null,this.listQuery.endTime=this.timeValue.length?Object(l.g)(this.timeValue[1]):null,this.listQuery.type=this.type?this.type:null,Object(o.d)(this.listQuery).then(function(t){e.returnOilData=t.list,e.total=t.totalCount,e.listLoading=!1})},getOutDetail:function(e){var t=this;Object(o.c)({backId:e}).then(function(e){t.gridData=e}).catch(function(e){})},statusChange:function(e){this.$refs.returnCans.getData(e)},listQueryCheck:function(){this.listQuery.tankNo=this.tankno||null,this.listQuery.startTime=this.timeValue?Object(l.g)(this.timeValue[0]):null,this.listQuery.endTime=this.timeValue?Object(l.g)(this.timeValue[1]):null},getHeader:function(){return[{prop:"tank_no",label:"罐号",minWidth:"65"},{prop:"true_volume",label:"回罐量"},{prop:"backRate",label:"回罐量毫米变化率"},{prop:"tableRate",label:"容积表毫米变化率"},{prop:"changeVolume",label:"液位变化量"},{prop:"start_level",label:"开始液位"},{prop:"end_level",label:"结束液位"},{prop:"start_volume",label:"开始体积"},{prop:"end_volume",label:"结束体积"},{prop:"startTime",label:"开始时间",width:160},{prop:"endTime",label:"结束时间",width:160},{prop:"reserved_field",label:"类型",formatter:function(e,t,n,a){return 1===n?"DIT判断":"系统判断"}}]},getChecked:function(){var e=[];for(var t in this.getHeader())e.push(this.getHeader()[t].label);return e},getNewTime:function(e){this.timeValue=e},changeBack:function(e){var t=this,n=1===e.reserved_field?"该条回罐数据为中石油DIT判断,":"";n+=e.back_tank?"是否改为非回罐":"是否改为回罐",this.$confirm(n,"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(o.a)({id:e.id,isBackToTank:!e.back_tank}).then(function(e){"100"===e.code&&(t.$message({type:"success",message:"修改成功"}),t.getAllReturnOilData())})}).catch(function(){t.$message({type:"info",message:"已取消"})})}}},m=(n("xCzG"),n("KHd+")),f=Object(m.a)(h,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"container"}},[n("div",{attrs:{id:"top"}},[n("span",{staticStyle:{"font-size":"16px"}},[e._v("罐号:")]),e._v(" "),n("el-select",{staticStyle:{width:"120px"},attrs:{clearable:""},model:{value:e.tankno,callback:function(t){e.tankno=t},expression:"tankno"}},e._l(e.tankOptions,function(e){return n("el-option",{key:e.tankNo,attrs:{label:e.detailInfo,value:e.tankNo}})})),e._v("\n       \n    "),n("span",{staticStyle:{"font-size":"16px"}},[e._v("类型:")]),e._v(" "),n("el-select",{attrs:{clearable:""},model:{value:e.type,callback:function(t){e.type=t},expression:"type"}},e._l(e.typeOptions,function(e){return n("el-option",{key:e.id,attrs:{label:e.label,value:e.id}})})),e._v("\n       \n    "),n("span",{staticStyle:{"font-size":"16px"}},[e._v(e._s(e.$t("table.time"))+":")]),e._v(" "),n("DatePicker",{ref:"timeVue",attrs:{"time-value":e.timeValue},on:{getTime:e.getNewTime}}),e._v(" "),n("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(t){e.listQuery.pageNum=1,e.getAllReturnOilData()}}},[e._v("搜索")]),e._v(" "),n("span",{staticClass:"tipsStyle"},[e._v("\n      【提示】体积单位:L  高度单位:mm\n    ")])],1),e._v(" "),n("handleTable",{ref:"returncans",staticClass:"out-table",staticStyle:{width:"100%"},attrs:{loading:e.listLoading,data:e.returnOilData,header:e.tableHeader,option:e.tableOption,height:e.height,defaultcheck:e.updateCheck,defaulheader:e.updateHeader,vuename:e.returncans,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{slot:"fixed",align:"center",label:"编辑回罐","min-width":"85px"},slot:"fixed",scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{slot:"reference",disabled:!t.row.back_tank,size:"mini",type:"text"},on:{click:function(n){e.statusChange(t.row)}},slot:"reference"},[e._v("\n          操作\n        ")])]}}])}),e._v(" "),n("el-table-column",{attrs:{slot:"fixed",align:"center",label:"是否回罐","min-width":"90px"},slot:"fixed",scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{slot:"reference",size:"mini",type:"text"},on:{click:function(n){e.changeBack(t.row)}},slot:"reference"},[e._v("\n          "+e._s(t.row.back_tank?"是":"否")+"\n        ")])]}}])}),e._v(" "),n("el-table-column",{attrs:{slot:"handled",align:"center",label:"付油详情"},slot:"handled",scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-popover",{attrs:{placement:"right",width:"512",trigger:"click"}},[n("el-table",{attrs:{data:e.gridData,border:"",stripe:""}},[n("el-table-column",{attrs:{width:"65",property:"nozzle_no",label:"枪号",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{width:"160",property:"backConfirmStartTime",label:"开始时间",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{width:"160",property:"backConfirmEndTime",label:"结束时间",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{width:"100",property:"volume",label:"付油量",align:"center"}})],1),e._v(" "),n("el-button",{attrs:{slot:"reference",size:"mini",type:"text"},on:{click:function(n){e.getOutDetail(t.row.id)}},slot:"reference"},[e._v("\n            查看\n          ")])],1)]}}])})],1),e._v(" "),n("div",{staticClass:"block"},[n("el-pagination",{attrs:{"current-page":e.listQuery.pageNum,"page-size":e.listQuery.pageRow,total:e.total,"page-sizes":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",background:""},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),e._v(" "),n("return-cans",{ref:"returnCans",on:{refresh:function(t){e.listQuery.pageNum=1,e.getAllReturnOilData()}}})],1)},[],!1,null,"0356afac",null);f.options.__file="return_cans.vue";t.default=f.exports},IAxT:function(e,t,n){"use strict";var a=n("stDE");n.n(a).a},RnhZ:function(e,t,n){var a={"./af":"K/tc","./af.js":"K/tc","./ar":"jnO4","./ar-dz":"o1bE","./ar-dz.js":"o1bE","./ar-kw":"Qj4J","./ar-kw.js":"Qj4J","./ar-ly":"HP3h","./ar-ly.js":"HP3h","./ar-ma":"CoRJ","./ar-ma.js":"CoRJ","./ar-sa":"gjCT","./ar-sa.js":"gjCT","./ar-tn":"bYM6","./ar-tn.js":"bYM6","./ar.js":"jnO4","./az":"SFxW","./az.js":"SFxW","./be":"H8ED","./be.js":"H8ED","./bg":"hKrs","./bg.js":"hKrs","./bm":"p/rL","./bm.js":"p/rL","./bn":"kEOa","./bn.js":"kEOa","./bo":"0mo+","./bo.js":"0mo+","./br":"aIdf","./br.js":"aIdf","./bs":"JVSJ","./bs.js":"JVSJ","./ca":"1xZ4","./ca.js":"1xZ4","./cs":"PA2r","./cs.js":"PA2r","./cv":"A+xa","./cv.js":"A+xa","./cy":"l5ep","./cy.js":"l5ep","./da":"DxQv","./da.js":"DxQv","./de":"tGlX","./de-at":"s+uk","./de-at.js":"s+uk","./de-ch":"u3GI","./de-ch.js":"u3GI","./de.js":"tGlX","./dv":"WYrj","./dv.js":"WYrj","./el":"jUeY","./el.js":"jUeY","./en-SG":"zavE","./en-SG.js":"zavE","./en-au":"Dmvi","./en-au.js":"Dmvi","./en-ca":"OIYi","./en-ca.js":"OIYi","./en-gb":"Oaa7","./en-gb.js":"Oaa7","./en-ie":"4dOw","./en-ie.js":"4dOw","./en-il":"czMo","./en-il.js":"czMo","./en-nz":"b1Dy","./en-nz.js":"b1Dy","./eo":"Zduo","./eo.js":"Zduo","./es":"iYuL","./es-do":"CjzT","./es-do.js":"CjzT","./es-us":"Vclq","./es-us.js":"Vclq","./es.js":"iYuL","./et":"7BjC","./et.js":"7BjC","./eu":"D/JM","./eu.js":"D/JM","./fa":"jfSC","./fa.js":"jfSC","./fi":"gekB","./fi.js":"gekB","./fo":"ByF4","./fo.js":"ByF4","./fr":"nyYc","./fr-ca":"2fjn","./fr-ca.js":"2fjn","./fr-ch":"Dkky","./fr-ch.js":"Dkky","./fr.js":"nyYc","./fy":"cRix","./fy.js":"cRix","./ga":"USCx","./ga.js":"USCx","./gd":"9rRi","./gd.js":"9rRi","./gl":"iEDd","./gl.js":"iEDd","./gom-latn":"DKr+","./gom-latn.js":"DKr+","./gu":"4MV3","./gu.js":"4MV3","./he":"x6pH","./he.js":"x6pH","./hi":"3E1r","./hi.js":"3E1r","./hr":"S6ln","./hr.js":"S6ln","./hu":"WxRl","./hu.js":"WxRl","./hy-am":"1rYy","./hy-am.js":"1rYy","./id":"UDhR","./id.js":"UDhR","./is":"BVg3","./is.js":"BVg3","./it":"bpih","./it-ch":"bxKX","./it-ch.js":"bxKX","./it.js":"bpih","./ja":"B55N","./ja.js":"B55N","./jv":"tUCv","./jv.js":"tUCv","./ka":"IBtZ","./ka.js":"IBtZ","./kk":"bXm7","./kk.js":"bXm7","./km":"6B0Y","./km.js":"6B0Y","./kn":"PpIw","./kn.js":"PpIw","./ko":"Ivi+","./ko.js":"Ivi+","./ku":"JCF/","./ku.js":"JCF/","./ky":"lgnt","./ky.js":"lgnt","./lb":"RAwQ","./lb.js":"RAwQ","./lo":"sp3z","./lo.js":"sp3z","./lt":"JvlW","./lt.js":"JvlW","./lv":"uXwI","./lv.js":"uXwI","./me":"KTz0","./me.js":"KTz0","./mi":"aIsn","./mi.js":"aIsn","./mk":"aQkU","./mk.js":"aQkU","./ml":"AvvY","./ml.js":"AvvY","./mn":"lYtQ","./mn.js":"lYtQ","./mr":"Ob0Z","./mr.js":"Ob0Z","./ms":"6+QB","./ms-my":"ZAMP","./ms-my.js":"ZAMP","./ms.js":"6+QB","./mt":"G0Uy","./mt.js":"G0Uy","./my":"honF","./my.js":"honF","./nb":"bOMt","./nb.js":"bOMt","./ne":"OjkT","./ne.js":"OjkT","./nl":"+s0g","./nl-be":"2ykv","./nl-be.js":"2ykv","./nl.js":"+s0g","./nn":"uEye","./nn.js":"uEye","./pa-in":"8/+R","./pa-in.js":"8/+R","./pl":"jVdC","./pl.js":"jVdC","./pt":"8mBD","./pt-br":"0tRk","./pt-br.js":"0tRk","./pt.js":"8mBD","./ro":"lyxo","./ro.js":"lyxo","./ru":"lXzo","./ru.js":"lXzo","./sd":"Z4QM","./sd.js":"Z4QM","./se":"//9w","./se.js":"//9w","./si":"7aV9","./si.js":"7aV9","./sk":"e+ae","./sk.js":"e+ae","./sl":"gVVK","./sl.js":"gVVK","./sq":"yPMs","./sq.js":"yPMs","./sr":"zx6S","./sr-cyrl":"E+lV","./sr-cyrl.js":"E+lV","./sr.js":"zx6S","./ss":"Ur1D","./ss.js":"Ur1D","./sv":"X709","./sv.js":"X709","./sw":"dNwA","./sw.js":"dNwA","./ta":"PeUW","./ta.js":"PeUW","./te":"XLvN","./te.js":"XLvN","./tet":"V2x9","./tet.js":"V2x9","./tg":"Oxv6","./tg.js":"Oxv6","./th":"EOgW","./th.js":"EOgW","./tl-ph":"Dzi0","./tl-ph.js":"Dzi0","./tlh":"z3Vd","./tlh.js":"z3Vd","./tr":"DoHr","./tr.js":"DoHr","./tzl":"z1FC","./tzl.js":"z1FC","./tzm":"wQk9","./tzm-latn":"tT3J","./tzm-latn.js":"tT3J","./tzm.js":"wQk9","./ug-cn":"YRex","./ug-cn.js":"YRex","./uk":"raLr","./uk.js":"raLr","./ur":"UpQW","./ur.js":"UpQW","./uz":"Loxo","./uz-latn":"AQ68","./uz-latn.js":"AQ68","./uz.js":"Loxo","./vi":"KSF8","./vi.js":"KSF8","./x-pseudo":"/X5v","./x-pseudo.js":"/X5v","./yo":"fzPg","./yo.js":"fzPg","./zh-cn":"XDpg","./zh-cn.js":"XDpg","./zh-hk":"SatO","./zh-hk.js":"SatO","./zh-tw":"kOpN","./zh-tw.js":"kOpN"};function i(e){var t=r(e);return n(t)}function r(e){if(!n.o(a,e)){var t=new Error("Cannot find module '"+e+"'");throw t.code="MODULE_NOT_FOUND",t}return a[e]}i.keys=function(){return Object.keys(a)},i.resolve=r,e.exports=i,i.id="RnhZ"},Rut5:function(e,t,n){"use strict";var a=n("7Qib"),i={name:"DatePicker",props:{timeValue:{type:Array,default:function(){return[]}}},data:function(){return{timeList:this.timeValue,pickerOptions:this.timePick()}},methods:{getTime:function(e){this.timeList=e},timePick:function(e){var t=Object(a.g)((new Date).getTime()-864e5).split(" ")[0],n=Object(a.g)((new Date).getTime()-1728e5).split(" ")[0],i=Object(a.g)((new Date).getTime()-5184e5).split(" ")[0];return{shortcuts:[{text:this.$t("table.today"),onClick:function(e){var t=new Date,n=new Date;t.setTime(new Date(t.toLocaleDateString()).getTime()),e.$emit("pick",[t,n])}},{text:this.$t("table.yesterday"),onClick:function(e){var n=t+" 00:00:00",a=t+" 23:59:59";e.$emit("pick",[n,a])}},{text:this.$t("table.nearly_three_days"),onClick:function(e){var t=n+" 00:00:00",a=new Date;e.$emit("pick",[t,a])}},{text:this.$t("table.nearly_aweek"),onClick:function(e){var t=i+" 00:00:00",n=new Date;e.$emit("pick",[t,n])}}]}},changeTime:function(e){this.$emit("getTime",e)}}},r=n("KHd+"),s=Object(r.a)(i,function(){var e=this,t=e.$createElement;return(e._self._c||t)("el-date-picker",{attrs:{"start-placeholder":e.$t("table.startTime"),"end-placeholder":e.$t("table.endTime"),"picker-options":e.pickerOptions,type:"datetimerange",align:"right"},on:{change:e.changeTime},model:{value:e.timeList,callback:function(t){e.timeList=t},expression:"timeList"}})},[],!1,null,null,null);s.options.__file="index.vue";t.a=s.exports},Vt1q:function(e,t,n){"use strict";n.d(t,"f",function(){return i}),n.d(t,"e",function(){return r}),n.d(t,"a",function(){return s}),n.d(t,"b",function(){return l}),n.d(t,"c",function(){return o}),n.d(t,"g",function(){return u}),n.d(t,"d",function(){return c}),n.d(t,"i",function(){return d}),n.d(t,"h",function(){return h});var a=n("t3Un");function i(e){return Object(a.a)({url:"/device/tank/list",method:"get",params:e})}function r(e){return Object(a.a)({url:"/device/tank/query",method:"get",params:e})}function s(e){return Object(a.a)({url:"/device/tank/add",method:"post",data:e})}function l(e){return Object(a.a)({url:"/device/tank/update",method:"post",data:e})}function o(e){return Object(a.a)({url:"/device/tank/exist",method:"post",data:e})}function u(e){return Object(a.a)({url:"/volumeTable/get",method:"get",params:e})}function c(e){return Object(a.a)({url:"/device/nozreltank/list",method:"get",params:e})}function d(e){return Object(a.a)({url:"/device/nozreltank/update",method:"post",data:e})}function h(e){return Object(a.a)({url:"/device/nozreltank/cancel",method:"post",data:e})}},hffR:function(e,t,n){"use strict";var a=n("gDS+"),i=n.n(a),r={props:{data:{default:function(){return[]},type:Array},header:{default:function(){return[]},type:Array},option:{default:function(){return{}},type:Object},defaultcheck:{default:function(){return[]},type:Array},defaulheader:{default:function(){return[]},type:Array},vuename:{default:function(){return""},type:String},callback:{type:Function,default:null},loading:{type:Boolean,default:!1},height:{default:function(){return window.screen.height>768?.595*window.screen.height:.64*window.screen.height},type:Number}},data:function(){return{tableHeader:this.header,dragState:{start:-9,end:-9,dragging:!1,direction:void 0},checkboxVal:this.defaultcheck,formThead:this.defaulheader,check_width:this.header.length>20?400:220}},watch:{checkboxVal:function(e,t){this.formThead=this.tableHeader.filter(function(t){return e.indexOf(t.label)>=0}),this.setCookie(this.vuename,i()(this.formThead)),this.setCookie(this.vuename+"_checked",'"'+e.toString()+'"'),this.key=this.key+1}},methods:{callbackClassName:function(e){var t=e.row,n=e.rowIndex;if(this.callback)return this.callback({row:t,rowIndex:n})},findHidden:function(){this.defaulheader.forEach(function(e){})},handleSelectionChange:function(e){this.$emit("onHandleSelectionChange",e)},rowClick:function(e,t,n){if(n&&"selection"!==n.type&&"operation"!==n.columnKey&&"expand"!==n.type){var a={row:e,event:t,column:n};this.$emit("onRowClick",a)}},renderHeader:function(e,t){var n=this,a=t.column;return e("div",{class:["thead-cell"],on:{mousedown:function(e){n.handleMouseDown(e,a)},mousemove:function(e){n.handleMouseMove(e,a)}}},[e("a",a.label),e("span",{class:["virtual"]})])},handleMouseDown:function(e,t){this.dragState.dragging=!0,this.dragState.start=parseInt(t.columnKey),document.addEventListener("mouseup",this.handleMouseUp)},handleMouseUp:function(){this.dragColumn(this.dragState),this.dragState={start:-9,end:-9,dragging:!1,direction:void 0},document.removeEventListener("mouseup",this.handleMouseUp)},handleMouseMove:function(e,t){if(!this.dragState.dragging)return!1;var n=parseInt(t.columnKey);n-this.dragState.start!=0?(this.dragState.direction=n-this.dragState.start<0?"left":"right",this.dragState.end=parseInt(t.columnKey)):this.dragState.direction=void 0},dragColumn:function(e){for(var t=e.start,n=e.end,a=[],r="left"===e.direction,s=r?n:t-1,l=r?t+1:n,o=0;o<this.formThead.length;o++)o===n?a.push(this.formThead[t]):o>s&&o<l?a.push(this.formThead[r?o-1:o+1]):a.push(this.formThead[o]);this.formThead=a,this.setCookie(this.vuename,i()(this.formThead))},headerCellClassName:function(e){e.column;var t=e.columnIndex;return(t===this.dragState.end?"darg_active_"+this.dragState.direction:"")+" "+(t===this.dragState.start?"darg_start":"")},cellClassName:function(e){e.column;return e.columnIndex===this.dragState.start?"darg_start":""},tableRowClassName:function(e){var t=e.row,n=e.rowIndex;this.$emit("tableRowClassName",{row:t,rowIndex:n})},setCookie:function(e,t){document.cookie=e+"="+t+"; "}}},s=(n("IAxT"),n("KHd+")),l=Object(s.a)(r,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"w-table",class:{"w-table_moving":e.dragState.dragging}},[n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],style:{width:parseInt(e.option.width)+"px"},attrs:{data:e.data,border:e.option.border,height:e.height,"max-height":e.option.maxHeight,"cell-class-name":e.cellClassName,"row-class-name":e.callbackClassName,"header-cell-class-name":e.headerCellClassName,"highlight-current-row":"",stripe:""},on:{"selection-change":e.handleSelectionChange,"row-click":e.rowClick}},[e._t("fixed"),e._v(" "),e._l(e.formThead,function(t,a){return n("el-table-column",{key:a,attrs:{prop:t.prop,label:e.$t(t.label),width:t.width,"min-width":t.minWidth,type:t.type,fixed:t.fixed,formatter:t.formatter,"header-align":t.headerAlign,"column-key":a.toString(),"render-header":e.renderHeader,align:"center","show-overflow-tooltip":""}})}),e._v(" "),e._t("handled")],2)],1)},[],!1,null,null,null);l.options.__file="handleTable.vue";t.a=l.exports},stDE:function(e,t,n){},xCzG:function(e,t,n){"use strict";var a=n("1C6H");n.n(a).a}}]);