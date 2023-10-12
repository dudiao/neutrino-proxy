(window.webpackJsonp=window.webpackJsonp||[]).push([[12],{338:function(t,s,a){"use strict";a.r(s);var n=a(7),e=Object(n.a)({},(function(){var t=this,s=t._self._c;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("div",{staticClass:"custom-block tip"},[s("p",{staticClass:"custom-block-title"},[t._v("提示")]),t._v(" "),s("p",[t._v("1.9.0版本，客户端配置格式有所调整。从之前的版本升级的，需要注意")])]),t._v(" "),s("h1",{attrs:{id:"以下是最新的客户端配置格式-app-yml"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#以下是最新的客户端配置格式-app-yml"}},[t._v("#")]),t._v(" 以下是最新的客户端配置格式(app.yml)")]),t._v(" "),s("div",{staticClass:"language-yml extra-class"},[s("pre",{pre:!0,attrs:{class:"language-yml"}},[s("code",[s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("neutrino")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("\n  "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("proxy")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("logger")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 日志级别")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("level")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("LOG_LEVEL"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("info"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("tunnel")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 线程池相关配置，用于技术调优，可忽略")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("thread-count")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("50")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 隧道SSL证书配置")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("key-store-password")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("STORE_PASS"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("123456")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("jks-path")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("JKS_PATH"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("classpath"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("/test.jks"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 服务端IP")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("server-ip")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("SERVER_IP"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("localhost"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 服务端端口(对应服务端app.yml中的tunnel.port、tunnel.ssl-port)")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("server-port")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("SERVER_PORT"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("9002")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 是否启用SSL(注意：该配置必须和server-port对应上)")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("ssl-enable")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("SSL_ENABLE"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token boolean important"}},[t._v("true")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 客户端连接唯一凭证")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("license-key")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("LICENSE_KEY"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 客户端唯一身份标识（可忽略，若不设置首次启动会自动生成）")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("client-id")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("CLIENT_ID"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 是否开启隧道传输报文日志(日志级别为debug时开启才有效)")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("transfer-log-enable")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("CLIENT_LOG"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token boolean important"}},[t._v("false")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 重连设置")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("reconnection")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 重连间隔（秒）")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("interval-seconds")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("10")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 是否开启无限重连(未开启时，客户端license不合法会自动停止应用，开启了则不会，请谨慎开启)")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("unlimited")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token boolean important"}},[t._v("false")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("client")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("\n      "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("udp")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 线程池相关配置，用于技术调优，可忽略")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("boss-thread-count")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("5")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("work-thread-count")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("20")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# udp傀儡端口范围")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("puppet-port-range")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" 10000"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("10500")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 是否开启隧道传输报文日志(日志级别为debug时开启才有效)")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token key atrule"}},[t._v("transfer-log-enable")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),t._v(" $"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("CLIENT_LOG"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token boolean important"}},[t._v("false")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])])])}),[],!1,null,null,null);s.default=e.exports}}]);