(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-2192","chunk-3ffb","chunk-76b9","chunk-2cc3"],{"0dzL":function(t,e,a){"use strict";a.r(e);var l=a("P2sY"),i=a.n(l),n=a("j7fn"),o=a("Vt1q"),s={name:"OilGunDevice",data:function(){var t=this,e=function(e,a,l){""===a||null===a||void 0===a?"nozzleNo"===e.field?l("油枪编号不能为空"):"detailInfo"===e.field?l("油枪名称不能为空"):l():t.getByteLen(a)>10?"nozzleNo"===e.field?l("油枪编号不能大于10字节"):"detailInfo"===e.field?l("油枪名称不能大于10字节"):l():t.validateStationData[e.field]!==a?"nozzleNo"===e.field?Object(n.c)({nozzleNo:a}).then(function(t){"fail"===t.info.result?l("油枪编号已存在"):l()}):"detailInfo"===e.field&&Object(n.c)({detailInfo:a}).then(function(t){"fail"===t.info.result?l("油枪名称已存在"):l()}):l()};return{addOilGunDevice:[],formLabelWidth:"85px",editFlag:[],allDisable:!0,form:{id:"",nozzleNo:"",tankNo:"",status:{value:1},inlineStatus:"ENABLE",oilName:""},formRules:{nozzleNo:[{validator:e,required:!0,trigger:"blur"}],detailInfo:[{validator:e,required:!0,trigger:"blur"}]},editBtnFlag:!1,dialogFormVisible:!1,dialogTitle:"添加加油枪",handleFlag:!1,tankOptions:[],oilName:"",itemIndex:"",validateStationData:{nozzleNo:"",detailInfo:""}}},created:function(){this.getAllTankList()},methods:{getOption:function(t){this.companyOptions=t},getAllTankList:function(){var t=this;Object(o.f)().then(function(e){e.forEach(function(e){t.tankOptions.push({tankNo:e.tankNo,tankName:e.detailInfo,oilName:e.oilName})}),t.getAllOilGunList()})},getAllOilGunList:function(){var t=this;Object(n.d)().then(function(e){t.addOilGunDevice=e.list;for(var a=0;a<t.addOilGunDevice.length;a++)t.editFlag[a]=!0,null===t.addOilGunDevice[a].onlineStatus&&(t.addOilGunDevice[a].onlineStatus={value:!1})})},getOilName:function(t){var e=this;""!==this.itemIndex?this.tankOptions.forEach(function(a){a.tankNo===t&&(e.addOilGunDevice[e.itemIndex].oilName=a.oilName)}):this.tankOptions.forEach(function(a){a.tankNo===t&&(e.form.oilName=a.oilName)})},addNozzle:function(){var t=this;this.handleFlag=!1,this.dialogTitle="添加加油枪",this.$nextTick(function(){void 0!==t.$refs.valObj&&(t.form.tankNo="",t.form.oilName="",t.form.status={value:1},t.$refs.valObj.resetFields())}),this.validateStationData.nozzleNo="",this.validateStationData.detailInfo="",this.dialogFormVisible=!0},editOilGunBtn:function(t){var e=this;this.handleFlag=!0,this.dialogTitle="编辑加油枪",this.dialogFormVisible=!0,this.$nextTick(function(){void 0!==e.$refs.valObj&&(e.form.status={value:t.status.value},e.$refs.valObj.resetFields(),e.form=i()({},t),e.validateStationData.nozzleNo=t.nozzleNo,e.validateStationData.detailInfo=t.detailInfo)})},submit:function(){var t=this;this.$refs.valObj.validate(function(e){if(!e)return!1;1===t.form.status.value?t.form.status="ENABLE":0===t.form.status.value&&(t.form.status="DISABLE"),t.form.onlineStatus="ENABLE",t.form.deviceType="NOZZLE",t.handleFlag?Object(n.b)(t.form).then(function(e){"success"===e.info.result&&(t.$message({message:"修改成功",type:"success"}),t.getAllOilGunList(),t.dialogFormVisible=!1)}):Object(n.a)(t.form).then(function(e){"success"===e.info.result&&(t.$message({message:"修改成功",type:"success"}),t.continueTip())})})},continueTip:function(){var t=this;this.$confirm("添加成功, 是否继续添加?","提示",{confirmButtonText:"是",cancelButtonText:"否",type:"success"}).then(function(){t.getAllOilGunList(),t.form.status={value:1}}).catch(function(){t.getAllOilGunList(),t.dialogFormVisible=!1})},getByteLen:function(t){return(t+="").replace(/[\u4E00-\uFFE5]/g,"aa").length}}},r=(a("SWi8"),a("KHd+")),c=Object(r.a)(s,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-button",{staticStyle:{float:"right","margin-bottom":"10px"},attrs:{size:"small",type:"primary",icon:"el-icon-search"},on:{click:t.addNozzle}},[t._v("\n    添加\n  ")]),t._v(" "),a("div",{staticStyle:{clear:"both"}}),t._v(" "),a("el-row",{staticStyle:{"margin-bottom":"20px"},attrs:{gutter:10}},t._l(t.addOilGunDevice,function(e,l){return a("el-col",{key:l,staticStyle:{"margin-bottom":"10px"},attrs:{span:12}},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",{staticStyle:{"font-size":"18px"}},[t._v(t._s(e.nozzleNo)+"号枪")]),t._v(" "),a("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:function(a){t.editOilGunBtn(e)}}},[t._v("\n            编辑\n          ")])],1),t._v(" "),a("el-form",[a("el-row",[a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"编号"}},[a("el-input",{staticStyle:{width:"130px"},attrs:{readonly:"",size:"small"},model:{value:e.nozzleNo,callback:function(a){t.$set(e,"nozzleNo","string"==typeof a?a.trim():a)},expression:"item.nozzleNo"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"名称"}},[a("el-input",{staticStyle:{width:"130px"},attrs:{readonly:"",size:"small"},model:{value:e.detailInfo,callback:function(a){t.$set(e,"detailInfo","string"==typeof a?a.trim():a)},expression:"item.detailInfo"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"关联油罐"}},[a("el-select",{staticStyle:{width:"130px"},attrs:{disabled:"",size:"small"},on:{change:t.getOilName},model:{value:e.tankNo,callback:function(a){t.$set(e,"tankNo",a)},expression:"item.tankNo"}},t._l(t.tankOptions,function(t){return a("el-option",{key:t.tankNo,attrs:{label:t.tankName,value:t.tankNo}})}))],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"油品"}},[a("el-input",{staticStyle:{width:"130px"},attrs:{readonly:"",size:"small"},model:{value:e.oilName,callback:function(a){t.$set(e,"oilName",a)},expression:"item.oilName"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty radioSty",attrs:{"label-width":t.formLabelWidth,label:"状态"}},[a("el-radio-group",{attrs:{disabled:""},model:{value:e.status.value,callback:function(a){t.$set(e.status,"value",a)},expression:"item.status.value"}},[a("el-radio",{attrs:{label:1,size:"small"}},[t._v("\n                    "+t._s(t.$t("table.able"))+"\n                  ")]),t._v(" "),a("el-radio",{attrs:{label:0,size:"small"}},[t._v("\n                    "+t._s(t.$t("table.disable"))+"\n                  ")])],1)],1)],1)],1)],1)],1)],1)})),t._v(" "),a("el-dialog",{directives:[{name:"el-drag-dialog",rawName:"v-el-drag-dialog"}],attrs:{visible:t.dialogFormVisible,title:t.dialogTitle,width:"951px"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"valObj",attrs:{model:t.form,rules:t.formRules}},[t._e(),t._v(" "),a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:t.$t("oil.code"),prop:"nozzleNo"}},[a("el-input",{staticStyle:{width:"130px"},attrs:{size:"small"},model:{value:t.form.nozzleNo,callback:function(e){t.$set(t.form,"nozzleNo","string"==typeof e?e.trim():e)},expression:"form.nozzleNo"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"名称",prop:"detailInfo"}},[a("el-input",{staticStyle:{width:"130px"},attrs:{size:"small"},model:{value:t.form.detailInfo,callback:function(e){t.$set(t.form,"detailInfo","string"==typeof e?e.trim():e)},expression:"form.detailInfo"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:t.$t("stationDevice.link_tank")}},[a("el-select",{staticStyle:{width:"130px"},attrs:{size:"small"},on:{change:t.getOilName},model:{value:t.form.tankNo,callback:function(e){t.$set(t.form,"tankNo",e)},expression:"form.tankNo"}},t._l(t.tankOptions,function(t){return a("el-option",{key:t.tankNo,attrs:{label:t.tankName,value:t.tankNo}})}))],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"油品"}},[a("el-input",{staticStyle:{width:"130px"},attrs:{readonly:"",size:"small"},model:{value:t.form.oilName,callback:function(e){t.$set(t.form,"oilName",e)},expression:"form.oilName"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty radioSty",attrs:{"label-width":t.formLabelWidth,label:t.$t("distribution_warn.status"),prop:"status.value"}},[a("el-radio-group",{model:{value:t.form.status.value,callback:function(e){t.$set(t.form.status,"value",e)},expression:"form.status.value"}},[a("el-radio",{attrs:{label:1,size:"small"}},[t._v("\n                "+t._s(t.$t("table.able"))+"\n              ")]),t._v(" "),a("el-radio",{attrs:{label:0,size:"small"}},[t._v("\n                "+t._s(t.$t("table.disable"))+"\n              ")])],1)],1)],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.submit}},[t._v("确 定")])],1)],1)],1)},[],!1,null,"fd4118ac",null);c.options.__file="oil_gun_device.vue";e.default=c.exports},"6IBr":function(t,e,a){"use strict";var l=a("tx5Q");a.n(l).a},D66H:function(t,e,a){},DAk5:function(t,e,a){},JjGQ:function(t,e,a){"use strict";a.d(e,"d",function(){return i}),a.d(e,"c",function(){return n}),a.d(e,"a",function(){return o}),a.d(e,"b",function(){return s});var l=a("t3Un");function i(t){return Object(l.a)({url:"/oil/list",method:"get",params:t})}function n(t){return Object(l.a)({url:"/oil/exist",method:"post",data:t})}function o(t){return Object(l.a)({url:"/oil/add",method:"post",data:t})}function s(t){return Object(l.a)({url:"/oil/update",method:"post",data:t})}},"Ka+h":function(t,e,a){},OmHL:function(t,e,a){"use strict";a.r(e);var l=a("P2sY"),i=a.n(l),n=a("XINx"),o=a("Vt1q"),s=a("JjGQ"),r={name:"TankDevice",directives:{elDragDialog:n.a},data:function(){var t=this,e=function(e,a,l){""===a||null===a||void 0===a?"tankNo"===e.field?l("油罐编号不能为空"):"detailInfo"===e.field?l("油罐名称不能为空"):l():t.getByteLen(a)>10?"tankNo"===e.field?l("油罐编号不能大于10字节"):"detailInfo"===e.field?l("油罐名称不能大于10字节"):l():t.validateStationData[e.field]!==a?"tankNo"===e.field?Object(o.c)({tankNo:a}).then(function(t){"fail"===t.info.result?l("油罐编号已存在"):l()}):"detailInfo"===e.field&&Object(o.c)({detailInfo:a}).then(function(t){"fail"===t.info.result?l("油罐名称已存在"):l()}):l()},a=function(e,a,l){""!==a&&t.getByteLen(a)>20?"probeManufactor"===e.field?l("液位仪厂家不能大于20字节"):"probeType"===e.field&&l("液位仪型号不能大于20字节"):l()};return{addTankDevice:[],formLabelWidthGuan:"100px",allDisable:!0,companyOptions:[],dialogFormVisible:!1,oilId:"",oilCode:"",oilName:"",form:{id:"",tankNo:"",detailInfo:"",productType:"",oilId:"",oilName:"",status:{value:1},diameter:"",maxVolume:"",waterBlind:"",oilBlind:"",highOilAlarm:"",highOilWarn:"",lowOilWarn:"",lowOilAlarm:"",highWaterAlarm:"",installTime:"",checkTime:"",maintenanceManagerId:"",probeManufactor:"",probeType:""},formRules:{tankNo:[{validator:e,required:!0,trigger:"blur"}],detailInfo:[{validator:e,required:!0,trigger:"blur"}],diameter:[{validator:a,required:!0,trigger:"blur"}],probeManufactor:[{validator:a,required:!0,trigger:"blur"}],probeType:[{validator:a,required:!0,trigger:"blur"}]},editBtnFlag:!1,oilList:[],dialogTitle:"添加油罐",handleFlag:!1,validateStationData:{tankNo:"",detailInfo:""}}},created:function(){this.getAllOil(),this.getAllTankList()},methods:{getOption:function(t){this.companyOptions=t},getAllOil:function(){var t=this;Object(s.d)({pageNum:1,pageRow:100}).then(function(e){t.oilList=e.list})},getAllTankList:function(){var t=this;Object(o.f)().then(function(e){t.addTankDevice=e;for(var a=function(e){t.oilList.forEach(function(a){a.code===t.addTankDevice[e].oilCode&&(t.addTankDevice[e].oilName=a.fullName)}),null===t.addTankDevice[e].status&&(t.addTankDevice[e].status={value:""})},l=0;l<t.addTankDevice.length;l++)a(l)})},changeOil:function(t){var e=this;this.oilList.forEach(function(a){a.id===t&&(e.form.oilId=a.id,e.form.oilCode=a.code)})},editTankBtn:function(t){var e=this;this.handleFlag=!0,this.dialogTitle="编辑油罐",this.dialogFormVisible=!0,this.$nextTick(function(){void 0!==e.$refs.valObj&&(e.form.status={value:t.status.value},e.$refs.valObj.resetFields(),e.form=i()({},t),e.validateStationData.tankNo=t.tankNo,e.validateStationData.detailInfo=t.detailInfo)})},addTank:function(){var t=this;this.handleFlag=!1,this.dialogTitle="添加油罐",this.$nextTick(function(){void 0!==t.$refs.valObj&&(t.form.status={value:1},t.$refs.valObj.resetFields())}),this.validateStationData.tankNo="",this.validateStationData.detailInfo="",this.dialogFormVisible=!0},submit:function(){var t=this;this.$refs.valObj.validate(function(e){if(!e)return!1;1===t.form.status.value?t.form.status="ENABLE":0===t.form.status.value&&(t.form.status="DISABLE"),t.form.deviceType="TANK",t.handleFlag?Object(o.b)(t.form).then(function(e){"success"===e.info.result&&(t.$message({message:"修改成功",type:"success"}),t.getAllTankList(),t.dialogFormVisible=!1)}):Object(o.a)(t.form).then(function(e){"success"===e.info.result&&(t.$message({message:"修改成功",type:"success"}),t.continueTip())})})},continueTip:function(){var t=this;this.$confirm("添加成功, 是否继续添加?","提示",{confirmButtonText:"是",cancelButtonText:"否",type:"success"}).then(function(){t.getAllTankList(),t.form.status={value:1}}).catch(function(){t.getAllTankList(),t.dialogFormVisible=!1})},getByteLen:function(t){return(t+="").replace(/[\u4E00-\uFFE5]/g,"aa").length}}},c=(a("6IBr"),a("KHd+")),d=Object(c.a)(r,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-button",{staticStyle:{float:"right","margin-bottom":"10px"},attrs:{size:"small",type:"primary",icon:"el-icon-search"},on:{click:t.addTank}},[t._v("\n    添加\n  ")]),t._v(" "),a("div",{staticStyle:{clear:"both"}}),t._v(" "),a("el-row",{staticStyle:{"margin-bottom":"20px"},attrs:{gutter:10}},t._l(t.addTankDevice,function(e,l){return a("el-col",{key:l,staticStyle:{"margin-bottom":"10px"},attrs:{span:12}},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",{staticStyle:{"font-size":"18px"}},[t._v(t._s(e.tankNo)+"号罐")]),t._v(" "),a("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:function(a){t.editTankBtn(e)}}},[t._v("\n            编辑\n          ")])],1),t._v(" "),a("el-form",[t._e(),t._v(" "),a("el-row",[a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:t.$t("oil.code")}},[a("el-input",{staticStyle:{width:"135px"},attrs:{readonly:"",size:"small"},model:{value:e.tankNo,callback:function(a){t.$set(e,"tankNo","string"==typeof a?a.trim():a)},expression:"item.tankNo"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:"名称"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{readonly:"",size:"small"},model:{value:e.detailInfo,callback:function(a){t.$set(e,"detailInfo",a)},expression:"item.detailInfo"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:t.$t("alarmParam.oil")}},[a("el-input",{staticStyle:{width:"135px"},attrs:{readonly:"",size:"small"},model:{value:e.oilName,callback:function(a){t.$set(e,"oilName",a)},expression:"item.oilName"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:t.$t("station_all.diameter")}},[a("el-input",{staticStyle:{width:"135px"},attrs:{readonly:"",size:"small"},model:{value:e.diameter,callback:function(a){t.$set(e,"diameter",a)},expression:"item.diameter"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:"液位仪厂家"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{readonly:"",size:"small"},model:{value:e.probeManufactor,callback:function(a){t.$set(e,"probeManufactor",a)},expression:"item.probeManufactor"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:"液位仪型号"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{readonly:"",size:"small"},model:{value:e.probeType,callback:function(a){t.$set(e,"probeType",a)},expression:"item.probeType"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty radioSty",attrs:{"label-width":t.formLabelWidthGuan,label:t.$t("distribution_warn.status")}},[a("el-radio-group",{attrs:{disabled:""},model:{value:e.status.value,callback:function(a){t.$set(e.status,"value",a)},expression:"item.status.value"}},[a("el-radio",{attrs:{label:1,size:"small"}},[t._v("\n                    "+t._s(t.$t("table.able"))+"\n                  ")]),t._v(" "),a("el-radio",{attrs:{label:0,size:"small"}},[t._v("\n                    "+t._s(t.$t("table.disable"))+"\n                  ")])],1)],1)],1)],1)],1)],1)],1)})),t._v(" "),a("el-dialog",{directives:[{name:"el-drag-dialog",rawName:"v-el-drag-dialog"}],attrs:{visible:t.dialogFormVisible,title:t.dialogTitle,width:"951px"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"valObj",attrs:{model:t.form,rules:t.formRules}},[t._e(),t._v(" "),a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:t.$t("oil.code"),prop:"tankNo"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{size:"small"},model:{value:t.form.tankNo,callback:function(e){t.$set(t.form,"tankNo","string"==typeof e?e.trim():e)},expression:"form.tankNo"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:"名称",prop:"detailInfo"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{size:"small"},model:{value:t.form.detailInfo,callback:function(e){t.$set(t.form,"detailInfo",e)},expression:"form.detailInfo"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:t.$t("alarmParam.oil"),prop:"oilName"}},[a("el-select",{staticStyle:{width:"135px"},attrs:{size:"small",placeholder:"请选择"},on:{change:t.changeOil},model:{value:t.form.oilName,callback:function(e){t.$set(t.form,"oilName",e)},expression:"form.oilName"}},t._l(t.oilList,function(t){return a("el-option",{key:t.id,attrs:{label:t.fullName,value:t.id}})}))],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:t.$t("station_all.diameter"),prop:"diameter"}},[a("el-input-number",{staticStyle:{width:"135px"},attrs:{min:0,max:3500,size:"small"},model:{value:t.form.diameter,callback:function(e){t.$set(t.form,"diameter",e)},expression:"form.diameter"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:"液位仪厂家",prop:"probeManufactor"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{size:"small"},model:{value:t.form.probeManufactor,callback:function(e){t.$set(t.form,"probeManufactor",e)},expression:"form.probeManufactor"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidthGuan,label:"液位仪型号",prop:"probeType"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{size:"small"},model:{value:t.form.probeType,callback:function(e){t.$set(t.form,"probeType",e)},expression:"form.probeType"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty radioSty",attrs:{"label-width":t.formLabelWidthGuan,label:t.$t("distribution_warn.status"),prop:"status.value"}},[a("el-radio-group",{model:{value:t.form.status.value,callback:function(e){t.$set(t.form.status,"value",e)},expression:"form.status.value"}},[a("el-radio",{attrs:{label:1,size:"small"}},[t._v("\n                "+t._s(t.$t("table.able"))+"\n              ")]),t._v(" "),a("el-radio",{attrs:{label:0,size:"small"}},[t._v("\n                "+t._s(t.$t("table.disable"))+"\n              ")])],1)],1)],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.submit}},[t._v("确 定")])],1)],1)],1)},[],!1,null,"a81d3890",null);d.options.__file="tank_device.vue";e.default=d.exports},SWi8:function(t,e,a){"use strict";var l=a("Ka+h");a.n(l).a},Vt1q:function(t,e,a){"use strict";a.d(e,"f",function(){return i}),a.d(e,"e",function(){return n}),a.d(e,"a",function(){return o}),a.d(e,"b",function(){return s}),a.d(e,"c",function(){return r}),a.d(e,"g",function(){return c}),a.d(e,"d",function(){return d}),a.d(e,"i",function(){return u}),a.d(e,"h",function(){return f});var l=a("t3Un");function i(t){return Object(l.a)({url:"/device/tank/list",method:"get",params:t})}function n(t){return Object(l.a)({url:"/device/tank/query",method:"get",params:t})}function o(t){return Object(l.a)({url:"/device/tank/add",method:"post",data:t})}function s(t){return Object(l.a)({url:"/device/tank/update",method:"post",data:t})}function r(t){return Object(l.a)({url:"/device/tank/exist",method:"post",data:t})}function c(t){return Object(l.a)({url:"/volumeTable/get",method:"get",params:t})}function d(t){return Object(l.a)({url:"/device/nozreltank/list",method:"get",params:t})}function u(t){return Object(l.a)({url:"/device/nozreltank/update",method:"post",data:t})}function f(t){return Object(l.a)({url:"/device/nozreltank/cancel",method:"post",data:t})}},Wur6:function(t,e,a){"use strict";a.r(e);var l=a("OmHL"),i=a("zYLz"),n=a("0dzL"),o=a("t3Un");var s={name:"DetailDetail",components:{TankDevice:l.default,TankerDevice:i.default,OilGunDevice:n.default},data:function(){return{activeName:"third",maintenanceOPtions:[]}},created:function(){this.getCompanyList()},methods:{getCompanyList:function(){var t,e=this;(t={noPage:1},Object(o.a)({url:"/device/maintenanceManager/list",method:"get",params:t})).then(function(t){for(var a=0;a<t.list.length;a++){var l={label:t.list[a].name,value:t.list[a].id};e.maintenanceOPtions.push(l),e.$refs.tank.getOption(e.maintenanceOPtions),e.$refs.tanker.getOption(e.maintenanceOPtions),e.$refs.gun.getOption(e.maintenanceOPtions)}})}}},r=(a("kzcE"),a("KHd+")),c=Object(r.a)(s,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"container"}},[a("el-tabs",{model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[a("el-tab-pane",{attrs:{name:"first"}},[a("span",{staticStyle:{display:"inline-block",width:"100px","margin-left":"35%"},attrs:{slot:"label"},slot:"label"},[t._v("\n        油罐\n      ")]),t._v(" "),a("tank-device",{ref:"tank"})],1),t._v(" "),a("el-tab-pane",{attrs:{name:"second"}},[a("span",{staticStyle:{display:"inline-block",width:"100px","margin-left":"32%"},attrs:{slot:"label"},slot:"label"},[t._v("\n        加油机\n      ")]),t._v(" "),a("tanker-device",{ref:"tanker"})],1),t._v(" "),a("el-tab-pane",{attrs:{name:"third"}},[a("span",{staticStyle:{display:"inline-block",width:"100px","margin-left":"32%"},attrs:{slot:"label"},slot:"label"},[t._v("\n        加油枪\n      ")]),t._v(" "),a("oil-gun-device",{ref:"gun"})],1)],1)],1)},[],!1,null,"e7f24072",null);c.options.__file="station_device.vue";e.default=c.exports},Xmhh:function(t,e,a){"use strict";var l=a("D66H");a.n(l).a},eaIu:function(t,e,a){"use strict";a.d(e,"e",function(){return i}),a.d(e,"a",function(){return n}),a.d(e,"b",function(){return o}),a.d(e,"c",function(){return s}),a.d(e,"d",function(){return r}),a.d(e,"g",function(){return c}),a.d(e,"f",function(){return d});var l=a("t3Un");function i(t){return Object(l.a)({url:"/device/dispenser/list",method:"get",params:t})}function n(t){return Object(l.a)({url:"/device/dispenser/add",method:"post",data:t})}function o(t){return Object(l.a)({url:"/device/dispenser/update",method:"post",data:t})}function s(t){return Object(l.a)({url:"/device/dispenser/exist",method:"post",data:t})}function r(t){return Object(l.a)({url:"/device/nozdisrel/list",method:"get",params:t})}function c(t){return Object(l.a)({url:"/device/nozdisrel/update",method:"post",data:t})}function d(t){return Object(l.a)({url:"/device/nozdisrel/cancel",method:"post",data:t})}},j7fn:function(t,e,a){"use strict";a.d(e,"d",function(){return i}),a.d(e,"a",function(){return n}),a.d(e,"b",function(){return o}),a.d(e,"c",function(){return s});var l=a("t3Un");function i(t){return Object(l.a)({url:"/device/nozzle/list",method:"get",params:t})}function n(t){return Object(l.a)({url:"/device/nozzle/add",method:"post",data:t})}function o(t){return Object(l.a)({url:"/device/nozzle/update",method:"post",data:t})}function s(t){return Object(l.a)({url:"/device/nozzle/exist",method:"post",data:t})}},kzcE:function(t,e,a){"use strict";var l=a("DAk5");a.n(l).a},tx5Q:function(t,e,a){},zYLz:function(t,e,a){"use strict";a.r(e);var l=a("P2sY"),i=a.n(l),n=a("eaIu"),o=a("Vt1q"),s={name:"TankerDevice",data:function(){var t=this,e=function(e,a,l){""===a||null===a||void 0===a?"dispenserNo"===e.field?l("加油机编号不能为空"):"detailInfo"===e.field?l("加油机名称不能为空"):l():t.getByteLen(a)>10?"dispenserNo"===e.field?l("加油机编号不能大于10字节"):"detailInfo"===e.field?l("加油机名称不能大于10字节"):l():t.validateStationData[e.field]!==a?"dispenserNo"===e.field?Object(n.c)({dispenserNo:a}).then(function(t){"fail"===t.info.result?l("加油机编号已存在"):l()}):"detailInfo"===e.field&&Object(n.c)({detailInfo:a}).then(function(t){"fail"===t.info.result?l("加油机名称已存在"):l()}):l()};return{addTankerDevice:[],formLabelWidth:"85px",precisionNum:0,allDisable:!0,deviceArr:[],cameraDeviceConn:[],tankDeviceConn:[],opUnits:[],customProp:[],companyOptions:[],dialogFormVisible:!1,dialogTitle:"添加加油机",form:{id:"",dispenserNo:"",productType:"",tankNos:"",status:{value:1},installTime:"",checkTime:"",maintenanceManagerId:"",oilName:""},formRules:{dispenserNo:[{validator:e,required:!0,trigger:"blur"}],detailInfo:[{validator:e,required:!0,trigger:"blur"}]},editBtnFlag:!1,tankOptions:[],oilName:"",handleFlag:!1,validateStationData:{dispenserNo:"",detailInfo:""}}},computed:{},created:function(){this.getAllTankList()},methods:{getOption:function(t){this.companyOptions=t},getAllTankList:function(){var t=this;Object(o.f)().then(function(e){e.forEach(function(e){t.tankOptions.push({tankNos:e.tankNo,tankName:e.detailInfo,oilName:e.oilName})}),t.getAllTankerList()})},getAllTankerList:function(){var t=this;Object(n.e)().then(function(e){t.addTankerDevice=e.list;for(var a=function(e){var a=t.addTankerDevice[e].tankNos.split(",");t.addTankerDevice[e].tankNos=[],t.addTankerDevice[e].oilName="",a.forEach(function(a){t.addTankerDevice[e].tankNos.push(parseInt(a)),t.tankOptions.forEach(function(l){parseInt(l.tankNos)===parseInt(a)&&(t.addTankerDevice[e].oilName+=l.oilName+",")})}),null===t.addTankerDevice[e].status&&(t.addTankerDevice[e].status={value:""})},l=0;l<t.addTankerDevice.length;l++)a(l)})},getOilName:function(t){var e=this;this.tankOptions.forEach(function(a){e.form.oilName="",t.forEach(function(t){e.tankOptions.forEach(function(a){a.tankNos===t&&(e.form.oilName+=a.oilName+",")})})})},editTankBtn:function(t){var e=this;this.handleFlag=!0,this.dialogTitle="编辑加油机",this.dialogFormVisible=!0,this.$nextTick(function(){void 0!==e.$refs.valObj&&(e.form.status={value:t.status.value},e.$refs.valObj.resetFields(),e.form=i()({},t),e.validateStationData.dispenserNo=t.dispenserNo,e.validateStationData.detailInfo=t.detailInfo)})},addTanker:function(){var t=this;this.handleFlag=!1,this.dialogTitle="添加加油机",this.$nextTick(function(){void 0!==t.$refs.valObj&&(t.form.oilName="",t.form.status={value:1},t.$refs.valObj.resetFields())}),this.validateStationData.dispenserNo="",this.validateStationData.detailInfo="",this.dialogFormVisible=!0},submit:function(){var t=this;this.form.deviceType="DISPENSER",this.form.tankNos=this.form.tankNos.join(","),this.$refs.valObj.validate(function(e){if(!e)return!1;1===t.form.status.value?t.form.status="ENABLE":0===t.form.status.value&&(t.form.status="DISABLE"),t.handleFlag?Object(n.b)(t.form).then(function(e){"success"===e.info.result&&(t.$message({message:"修改成功",type:"success"}),t.getAllTankerList(),t.dialogFormVisible=!1)}):Object(n.a)(t.form).then(function(e){"success"===e.info.result&&(t.$message({message:"修改成功",type:"success"}),t.continueTip())})})},continueTip:function(){var t=this;this.$confirm("添加成功, 是否继续添加?","提示",{confirmButtonText:"是",cancelButtonText:"否",type:"success"}).then(function(){t.getAllTankerList(),t.form.status={value:1}}).catch(function(){t.getAllTankerList(),t.dialogFormVisible=!1})},getByteLen:function(t){return(t+="").replace(/[\u4E00-\uFFE5]/g,"aa").length}}},r=(a("Xmhh"),a("KHd+")),c=Object(r.a)(s,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-button",{staticStyle:{float:"right","margin-bottom":"10px"},attrs:{size:"small",type:"primary",icon:"el-icon-search"},on:{click:t.addTanker}},[t._v("\n    添加\n  ")]),t._v(" "),a("div",{staticStyle:{clear:"both"}}),t._v(" "),a("el-row",{staticStyle:{"margin-bottom":"20px"},attrs:{gutter:10}},t._l(t.addTankerDevice,function(e,l){return a("el-col",{key:l,staticStyle:{"margin-bottom":"10px"},attrs:{span:12}},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",{staticStyle:{"font-size":"18px"}},[t._v(t._s(e.dispenserNo)+"号加油机")]),t._v(" "),a("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:function(a){t.editTankBtn(e)}}},[t._v("\n            编辑\n          ")])],1),t._v(" "),a("el-form",[t._e(),t._v(" "),a("el-row",[a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:t.$t("oil.code")}},[a("el-input",{staticStyle:{width:"135px"},attrs:{readonly:"",size:"small"},model:{value:e.dispenserNo,callback:function(a){t.$set(e,"dispenserNo","string"==typeof a?a.trim():a)},expression:"item.dispenserNo"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"名称"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{readonly:"",size:"small"},model:{value:e.detailInfo,callback:function(a){t.$set(e,"detailInfo","string"==typeof a?a.trim():a)},expression:"item.detailInfo"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{label:"关联油罐","label-width":"85px"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{disabled:"",size:"small",multiple:""},on:{change:t.getOilName},model:{value:e.tankNos,callback:function(a){t.$set(e,"tankNos",a)},expression:"item.tankNos"}},t._l(t.tankOptions,function(t){return a("el-option",{key:t.tankNos,attrs:{label:t.tankName,value:t.tankNos}})}))],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"油品"}},[a("el-input",{staticStyle:{width:"100%"},attrs:{readonly:"",size:"small"},model:{value:e.oilName,callback:function(a){t.$set(e,"oilName",a)},expression:"item.oilName"}})],1)],1),t._v(" "),a("el-col",{attrs:{xl:8,lg:12}},[a("el-form-item",{staticClass:"inputSty radioSty",attrs:{"label-width":t.formLabelWidth,label:t.$t("distribution_warn.status")}},[a("el-radio-group",{attrs:{disabled:""},model:{value:e.status.value,callback:function(a){t.$set(e.status,"value",a)},expression:"item.status.value"}},[a("el-radio",{attrs:{label:1,size:"small"}},[t._v("\n                    "+t._s(t.$t("table.able"))+"\n                  ")]),t._v(" "),a("el-radio",{attrs:{label:0,size:"small"}},[t._v("\n                    "+t._s(t.$t("table.disable"))+"\n                  ")])],1)],1)],1)],1)],1)],1)],1)})),t._v(" "),a("el-dialog",{directives:[{name:"el-drag-dialog",rawName:"v-el-drag-dialog"}],attrs:{visible:t.dialogFormVisible,title:t.dialogTitle,width:"951px"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"valObj",attrs:{model:t.form,rules:t.formRules}},[t._e(),t._v(" "),a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:t.$t("oil.code"),prop:"dispenserNo"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{size:"small"},model:{value:t.form.dispenserNo,callback:function(e){t.$set(t.form,"dispenserNo","string"==typeof e?e.trim():e)},expression:"form.dispenserNo"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"名称",prop:"detailInfo"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{size:"small"},model:{value:t.form.detailInfo,callback:function(e){t.$set(t.form,"detailInfo","string"==typeof e?e.trim():e)},expression:"form.detailInfo"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{label:"关联油罐","label-width":"85px",prop:"tankNos"}},[a("el-select",{staticStyle:{width:"135px"},attrs:{size:"small",multiple:"","collapse-tags":""},on:{change:t.getOilName},model:{value:t.form.tankNos,callback:function(e){t.$set(t.form,"tankNos",e)},expression:"form.tankNos"}},t._l(t.tankOptions,function(t){return a("el-option",{key:t.tankNos,attrs:{label:t.tankName,value:t.tankNos}})}))],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty",attrs:{"label-width":t.formLabelWidth,label:"油品"}},[a("el-input",{staticStyle:{width:"135px"},attrs:{readonly:"",size:"small"},model:{value:t.form.oilName,callback:function(e){t.$set(t.form,"oilName",e)},expression:"form.oilName"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{staticClass:"inputSty radioSty",attrs:{"label-width":t.formLabelWidth,label:t.$t("distribution_warn.status"),prop:"status.value"}},[a("el-radio-group",{model:{value:t.form.status.value,callback:function(e){t.$set(t.form.status,"value",e)},expression:"form.status.value"}},[a("el-radio",{attrs:{label:1,size:"small"}},[t._v("\n                "+t._s(t.$t("table.able"))+"\n              ")]),t._v(" "),a("el-radio",{attrs:{label:0,size:"small"}},[t._v("\n                "+t._s(t.$t("table.disable"))+"\n              ")])],1)],1)],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.submit}},[t._v("确 定")])],1)],1)],1)},[],!1,null,"bd9d248c",null);c.options.__file="tanker_device.vue";e.default=c.exports}}]);