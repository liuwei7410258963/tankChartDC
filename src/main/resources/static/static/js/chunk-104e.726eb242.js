(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-104e"],{"1auE":function(t,e,i){t.exports=i.p+"static/img/log.8bca349.png"},CDzP:function(t,e,i){t.exports=i.p+"static/img/erwei.8bb0d3e.jpg"},Cl1Y:function(t,e,i){},MzoB:function(t,e,i){"use strict";var n=i("ieBe");i.n(n).a},c11S:function(t,e,i){"use strict";var n=i("Cl1Y");i.n(n).a},ieBe:function(t,e,i){},nj19:function(t,e,i){"use strict";var n=i("p1cE");i.n(n).a},ntYl:function(t,e,i){"use strict";i.r(e);var n={computed:{language:function(){return this.$store.getters.language}},methods:{handleSetLanguage:function(t){this.$i18n.locale=t,this.$store.dispatch("setLanguage",t),this.$message({message:"Switch Language Success",type:"success"})}}},o=(i("MzoB"),i("KHd+")),s=Object(o.a)(n,function(){var t=this.$createElement,e=this._self._c||t;return e("el-dropdown",{staticClass:"international",attrs:{trigger:"click"},on:{command:this.handleSetLanguage}},[e("div",[e("svg-icon",{attrs:{"class-name":"international-icon","icon-class":"language"}})],1),this._v(" "),e("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[e("el-dropdown-item",{attrs:{disabled:"zh"===this.language,command:"zh"}},[this._v("中文")]),this._v(" "),e("el-dropdown-item",{attrs:{disabled:"en"===this.language,command:"en"}},[this._v("English")])],1)],1)},[],!1,null,"78105896",null);s.options.__file="index.vue";var a=s.exports,r={name:"SIdentify",props:{identifyCode:{type:String,default:"1234"},fontSizeMin:{type:Number,default:26},fontSizeMax:{type:Number,default:40},backgroundColorMin:{type:Number,default:180},backgroundColorMax:{type:Number,default:240},colorMin:{type:Number,default:20},colorMax:{type:Number,default:160},lineColorMin:{type:Number,default:150},lineColorMax:{type:Number,default:250},dotColorMin:{type:Number,default:180},dotColorMax:{type:Number,default:250},contentWidth:{type:Number,default:112},contentHeight:{type:Number,default:40}},watch:{identifyCode:function(){this.drawPic()}},mounted:function(){this.drawPic()},methods:{randomNum:function(t,e){return Math.floor(Math.random()*(e-t)+t)},randomColor:function(t,e){return"rgb("+this.randomNum(t,e)+","+this.randomNum(t,e)+","+this.randomNum(t,e)+")"},drawPic:function(){var t=document.getElementById("s-canvas").getContext("2d");t.textBaseline="bottom",t.fillStyle=this.randomColor(this.backgroundColorMin,this.backgroundColorMax),t.fillRect(0,0,this.contentWidth,this.contentHeight);for(var e=0;e<this.identifyCode.length;e++)this.drawText(t,this.identifyCode[e],e);this.drawLine(t),this.drawDot(t)},drawText:function(t,e,i){t.fillStyle=this.randomColor(this.colorMin,this.colorMax),t.font=this.randomNum(this.fontSizeMin,this.fontSizeMax)+"px SimHei";var n=(i+1)*(this.contentWidth/(this.identifyCode.length+1)),o=this.randomNum(this.fontSizeMax,this.contentHeight-5),s=this.randomNum(-45,45);t.translate(n,o),t.rotate(s*Math.PI/180),t.fillText(e,0,0),t.rotate(-s*Math.PI/180),t.translate(-n,-o)},drawLine:function(t){for(var e=0;e<8;e++)t.strokeStyle=this.randomColor(this.lineColorMin,this.lineColorMax),t.beginPath(),t.moveTo(this.randomNum(0,this.contentWidth),this.randomNum(0,this.contentHeight)),t.lineTo(this.randomNum(0,this.contentWidth),this.randomNum(0,this.contentHeight)),t.stroke()},drawDot:function(t){for(var e=0;e<100;e++)t.fillStyle=this.randomColor(this.dotColorMin,this.dotColorMax),t.beginPath(),t.arc(this.randomNum(0,this.contentWidth),this.randomNum(0,this.contentHeight),1,0,2*Math.PI),t.fill()}}},l=Object(o.a)(r,function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"s-canvas"},[e("canvas",{attrs:{id:"s-canvas",width:this.contentWidth,height:this.contentHeight}})])},[],!1,null,null,null);l.options.__file="index.vue";var d=l.exports,c=i("rYxZ"),u=i.n(c),h=i("1auE"),m=i.n(h),g=i("CDzP"),f=i.n(g),p=i("Q2AE");function w(t){return function(t,e){for(var i="",n=0;n<e;n++)i+=t[(o=0,s=t.length,Math.floor(Math.random()*(s-o)+o))];var o,s;return p.a.state.loginCode=i,i}(t,4)}var v=i("X4fA"),y={name:"Login",components:{LangSelect:a,SIdentify:d},data:function(){var t=this;return{height:window.screen.height+"px",loginForm:{userName:"",pwd:"",identifyCode:""},identifyCodes:"1234567890",identifyCode:"",loginRules:{userName:[{required:!0,message:this.$t("login.input"),trigger:"blur"}],pwd:[{required:!0,message:this.$t("login.input_pass"),trigger:"blur"}],identifyCode:[{required:!0,validator:function(e,i,n){""===i?n(t.$t("login.yzCode")):i!==t.identifyCode?(n(t.$t("login.correct_yzCode")),t.loginForm.identifyCode="",t.refreshYzCode()):n()},trigger:"blur"}]},loading:!1,pwdType:"password",redirect:void 0,imagePathSx:{backgroundImage:"url("+i("rYxZ")+")",backgroundRepeat:"no-repeat",width:"72%",backgroundSize:"100% 100%"},eye:"eye",createdPwdShow:!1,numPwdKey:0,pwdHasFlag:!1,login:u.a,logo:m.a,erwei:f.a,formItem:window.screen.height>768?"8%":"3%",codeItem:window.screen.height>768?"60%":"50%",qrCode:window.screen.height>768?"10%":"5%"}},computed:{myValue:function(){return this.$store.state.loginCode}},watch:{$route:{handler:function(t){this.redirect=t.query&&t.query.redirect},immediate:!0},myValue:function(t,e){this.identifyCode=this.$store.state.loginCode}},mounted:function(){this.checkBrowser().chrome||this.$notify({title:"建议",message:"为了给您带来更好的使用体验，我们强烈建议您使用极速模式或者Chrome浏览器",position:"top-left",duration:0}),this.refreshYzCode()},methods:{checkBrowser:function(){var t=navigator.userAgent.toLocaleLowerCase(),e={};if(null!=t.match(/msie/)||null!=t.match(/trident/))"IE",e.ie=!0;else if(null!=t.match(/firefox/))"火狐",e.firefox=!0;else if(null!=t.match(/ubrowser/))"UC",e.uc=!0;else if(null!=t.match(/opera/))"欧朋",e.opera=!0;else if(null!=t.match(/bidubrowser/))"百度",e.baidu=!0;else if(null!=t.match(/metasr/))"搜狗",e.metasr=!0;else if(null!=t.match(/tencenttraveler/)||null!=t.match(/qqbrowse/))"QQ",e.qq=!0;else if(null!=t.match(/maxthon/))"遨游",e.maxthon=!0;else if(null!=t.match(/chrome/)){this._mime("type","application/vnd.chromium.remoting-viewer")?("360",e.is360=!0):("谷歌",e.chrome=!0)}else null!=t.match(/safari/)&&("Safari",e.safari=!0);return e},_mime:function(t,e){var i=navigator.mimeTypes;for(var n in i)if(i[n][t]===e)return!0;return!1},pwdFocus:function(){0===this.numPwdKey&&(this.createdPwdShow=!1),this.numPwdKey++},pwdInput:function(){this.loginForm.pwd.length>1&&1===this.numPwdKey?this.createdPwdShow=!1:1===this.loginForm.pwd.length&&(this.createdPwdShow=!0)},showPwd:function(){"password"===this.pwdType?(this.pwdType="",this.eye="display"):(this.eye="eye",this.pwdType="password")},refreshYzCode:function(){this.identifyCode=w(this.identifyCodes)},handleLogin:function(){var t=this;this.$store.state.loginPwd=this.loginForm.pwd,this.$refs.loginForm.validate(function(e){if(!e)return!1;t.$store.dispatch("Login",t.loginForm,function(t){}).then(function(){Object(v.e)(),t.$router.push({path:"/dashboard"})}).catch(function(){t.loginForm.identifyCode="",t.refreshYzCode(),t.loading=!1})})}}},C=(i("c11S"),i("nj19"),Object(o.a)(y,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"login-container"},[i("img",{staticClass:"logo-img",attrs:{src:t.login}}),t._v(" "),i("div",{staticClass:"login-positon"},[i("img",{staticClass:"logo-img",attrs:{src:t.logo}}),t._v(" "),i("p",{staticClass:"title"},[t._v("容 积 表 诊 断 与 校 正")]),t._v(" "),i("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:t.loginForm,rules:t.loginRules,"auto-complete":"on","label-position":"left"}},[i("el-form-item",{style:"margin-bottom:"+t.formItem,attrs:{prop:"userName"}},[i("span",{staticClass:"svg-container svg-container_login"},[i("svg-icon",{attrs:{"icon-class":"admin"}})],1),t._v(" "),i("el-input",{staticClass:"username",attrs:{placeholder:t.$t("login.username"),name:"username",type:"text","auto-complete":"off",size:"on"},model:{value:t.loginForm.userName,callback:function(e){t.$set(t.loginForm,"userName",e)},expression:"loginForm.userName"}})],1),t._v(" "),i("el-form-item",{style:"margin-bottom:"+t.formItem,attrs:{prop:"pwd"}},[i("span",{staticClass:"svg-container svg-container_login"},[i("svg-icon",{attrs:{"icon-class":"pwd"}})],1),t._v(" "),i("el-input",{attrs:{type:t.pwdType,placeholder:t.$t("login.password"),name:"password","auto-complete":"on"},on:{focus:t.pwdFocus,input:t.pwdInput},model:{value:t.loginForm.pwd,callback:function(e){t.$set(t.loginForm,"pwd",e)},expression:"loginForm.pwd"}}),t._v(" "),i("span",{directives:[{name:"show",rawName:"v-show",value:t.createdPwdShow,expression:"createdPwdShow"}],staticClass:"show-pwd",on:{click:t.showPwd}},[i("svg-icon",{attrs:{"icon-class":t.eye}})],1)],1),t._v(" "),i("el-form-item",{style:"margin-bottom:"+t.formItem,attrs:{prop:"identifyCode"}},[i("span",{staticClass:"svg-container svg-container_login"},[i("svg-icon",{attrs:{"icon-class":"vfc"}})],1),t._v(" "),i("el-input",{staticClass:"codeStyle",style:"width:"+t.codeItem,attrs:{placeholder:t.$t("login.yzCodes"),type:"text",name:"identifyCode","auto-complete":"on"},nativeOn:{keyup:function(e){return"button"in e||!t._k(e.keyCode,"enter",13,e.key,"Enter")?t.handleLogin(e):null}},model:{value:t.loginForm.identifyCode,callback:function(e){t.$set(t.loginForm,"identifyCode",e)},expression:"loginForm.identifyCode"}}),t._v(" "),i("span",{staticClass:"code",on:{click:t.refreshYzCode}},[i("s-identify",{attrs:{"identify-code":t.identifyCode}})],1)],1),t._v(" "),i("el-form-item",[i("el-button",{staticClass:"login-btn",attrs:{loading:t.loading},nativeOn:{click:function(e){return e.preventDefault(),t.handleLogin(e)}}},[t._v("\n          "+t._s(t.$t("login.logIn"))+"\n        ")])],1)],1),t._v(" "),i("div",{staticClass:"qr-code",style:"marginTop:"+t.qrCode},[i("img",{staticClass:"qr-img",attrs:{src:t.erwei}})]),t._v(" "),t._m(0)],1)])},[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"bottom-box"},[e("hr"),this._v(" "),e("div",[e("p",{attrs:{align:"center"}},[this._v("Tel : 400-7089-185")]),this._v(" "),e("p",{attrs:{align:"center"}},[this._v("\n          容积表诊断与校正系统 Ver 1.0版权所有(C) 青岛澳科软件有限公司\n        ")])])])}],!1,null,"cc40c344",null));C.options.__file="index.vue";e.default=C.exports},p1cE:function(t,e,i){},rYxZ:function(t,e,i){t.exports=i.p+"static/img/login.29ad66d.jpg"}}]);