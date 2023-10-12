(window.webpackJsonp=window.webpackJsonp||[]).push([[28],{353:function(r,e,t){"use strict";t.r(e);var _=t(7),v=Object(_.a)({},(function(){var r=this,e=r._self._c;return e("ContentSlotsDistributor",{attrs:{"slot-key":r.$parent.slotKey}},[e("h1",{attrs:{id:"🎋代码分支说明"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#🎋代码分支说明"}},[r._v("#")]),r._v(" 🎋代码分支说明")]),r._v(" "),e("ul",[e("li",[r._v("dev：常规开发分支，日常的开发、Bug修复、提交PR都在此分支")]),r._v(" "),e("li",[r._v("feature/xxx：特征分支，一些试验性开发、提交PR都在此分支，xxx可自行取一个有意义的名称")]),r._v(" "),e("li",[r._v("release/xxx：发行版分支，作为阶段性成果、重大更新的版本固化分支。受保护，不允许任何提交。")]),r._v(" "),e("li",[r._v("master：主分支，定期同步最新代码，目前仅支持本人(傲世孤尘/雨韵诗泽)提交。")])]),r._v(" "),e("h1",{attrs:{id:"✊贡献的方式"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#✊贡献的方式"}},[r._v("#")]),r._v(" ✊贡献的方式")]),r._v(" "),e("p",[r._v("包括，但不限于以下形式：")]),r._v(" "),e("ul",[e("li",[r._v("提交"),e("a",{attrs:{href:"https://gitee.com/dromara/neutrino-proxy/issues",target:"_blank",rel:"noopener noreferrer"}},[r._v("issues"),e("OutboundLink")],1)]),r._v(" "),e("li",[r._v("参与"),e("a",{attrs:{href:"https://gitee.com/dromara/neutrino-proxy/issues",target:"_blank",rel:"noopener noreferrer"}},[r._v("issues"),e("OutboundLink")],1),r._v("解决的必要性、解决方案的讨论、给出建设性的意见")]),r._v(" "),e("li",[r._v("领取"),e("a",{attrs:{href:"https://gitee.com/dromara/neutrino-proxy/issues",target:"_blank",rel:"noopener noreferrer"}},[r._v("issues"),e("OutboundLink")],1),r._v(" 并完成开发、提交PR")]),r._v(" "),e("li",[r._v("为"),e("a",{attrs:{href:"https://gitee.com/dromara/neutrino-proxy",target:"_blank",rel:"noopener noreferrer"}},[r._v("本项目"),e("OutboundLink")],1),r._v("完善代码注释")]),r._v(" "),e("li",[r._v("为"),e("a",{attrs:{href:"https://gitee.com/dromara/neutrino-proxy",target:"_blank",rel:"noopener noreferrer"}},[r._v("本项目"),e("OutboundLink")],1),r._v("增加测试代码")]),r._v(" "),e("li",[r._v("为"),e("a",{attrs:{href:"https://gitee.com/dromara/neutrino-proxy",target:"_blank",rel:"noopener noreferrer"}},[r._v("本项目"),e("OutboundLink")],1),r._v("优化现有代码")]),r._v(" "),e("li",[r._v("为"),e("a",{attrs:{href:"https://gitee.com/dromara/neutrino-proxy",target:"_blank",rel:"noopener noreferrer"}},[r._v("本项目"),e("OutboundLink")],1),r._v("优化操作体验")]),r._v(" "),e("li",[r._v("通过公众号、博客、贴吧、论坛等形式分享/宣传中微子代理")]),r._v(" "),e("li",[r._v("丰富项目文档，包括使用过程中踩过的坑、需要优化的点、具体问题的解决方法等")])]),r._v(" "),e("h1",{attrs:{id:"🧬贡献代码的步骤"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#🧬贡献代码的步骤"}},[r._v("#")]),r._v(" 🧬贡献代码的步骤")]),r._v(" "),e("ul",[e("li",[r._v("在Gitee或者Github上fork项目到自己的repo，fork，一定要把项目fork一份。")]),r._v(" "),e("li",[r._v("把fork过去的项目也就是你的项目clone到你的本地")]),r._v(" "),e("li",[r._v("同步feature/1.7.1最新代码")]),r._v(" "),e("li",[r._v("修改代码")]),r._v(" "),e("li",[r._v("开发完成后，不忙着提PR，再拉一遍最新代码，如果有冲突、解决冲突")]),r._v(" "),e("li",[r._v("commit后push到自己的库")]),r._v(" "),e("li",[r._v("登录Gitee在你首页可以看到一个 pull request 按钮，点击它，填写一些说明信息，然后提交即可。")]),r._v(" "),e("li",[r._v("等待维护者合并")])]),r._v(" "),e("h1",{attrs:{id:"📐pr的规范"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#📐pr的规范"}},[r._v("#")]),r._v(" 📐PR的规范")]),r._v(" "),e("blockquote",[e("p",[r._v("你可能发现有的代码并不符合这个规范，但我们后续都会朝着这个方向迈进。后续我们会逐步全面规范化，请先保证新提交的代码满足以下要求：")])]),r._v(" "),e("ul",[e("li",[r._v("注释完备，尤其每个新增的方法应按照Java文档规范标明方法说明、参数说明、返回值说明等信息，必要时请添加单元测试，如果愿意，也可以加上你的大名。")]),r._v(" "),e("li",[r._v("原则上，不允许自行添加第三方依赖库。若确有必要，请先和项目负责人沟通达成一致。")]),r._v(" "),e("li",[r._v("对现有代码的优化，请在注释中说明原因。")]),r._v(" "),e("li",[r._v("如果涉及到数据库的变更，则至少需要做到以下绩点：\n"),e("ul",[e("li",[r._v("sqlite、mysql下均测试通过")]),r._v(" "),e("li",[r._v("在"),e("code",[r._v("./neutrino-proxy-server/src/main/resource/sql")]),r._v("下维护更新对应的表结构sql文件")]),r._v(" "),e("li",[r._v("对于新增的表，维护必要的初始化数据")]),r._v(" "),e("li",[r._v("在对应的mysql、sqlite的update目录下，维护SQL变更记录，确保SQL执行测试通过")])])])])])}),[],!1,null,null,null);e.default=v.exports}}]);