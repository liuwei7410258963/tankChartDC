# 容积表校正与标定项目
## 简介
采用  
- 后端技术：SpringBoot + Mybatis-Plus +Mysql(数据库)
- 前端技术: Vue



# 需求用例安排

进度
![](./doc/进度.png)

主要需求进度安排
1. 基础信息维护用例（用户级）
2. 整理数据U
3. 确认数据用例U
4. 接收DIT数据
5. 诊断容积表U
6. 用户导出确认数据U

计划任务安排：
- 第一周~ 第二周周四
    - 基础信息维护用例（用户级）--杨娇、流程
    - 整理数据U --孙彪龙
- 第二周周五~ 第三周周五
    - 确认数据用例U --孙彪龙、刘威（后端部分）
    - 前端进度看进度进行安排 
- 第四周~ 第四周周末
    - 检查容积表诊断结果U--孙彪龙
    - 接收DIT数据--刘威
    - 用户导出确认数据U--刘威
    - 前端进度看进度进行安排 
- 第五周
    - 后端，测试完善
    - 前端继续跟进

测试 问题

2020-04-21至2020-04-24
付油组合液位组关联关系21 杨娇、孙彪龙
液位处理数据保存测试（） 21-22 刘威
回罐-23（回罐界面调整） 杨娇、孙彪龙
液位逻辑调整22 孙彪龙

2020-04-26至2020-04-30
软件-刘威 
- 4.26至4.27 开发回罐接口（不联调）和校正图的查询付油组和液位轨迹接口。
- 4.28开发校正图的付油详情和轨迹详情、增加回罐接口和改变变化率（不联调）
- 4.29 联调回罐所有接口
- 4.30 付油组后台接口和卸油确认接口、联调校正图
- 五一后：联调校正图
杨娇
- 付油组确认:   时间:0.5-1 
- 回罐确认:  时间:1 - 1.5 
- 卸油确认:   时间:0.5 -1 
- 液位组与付油组  时间:0.5 -1 

孙彪龙
液位逻辑调整

5月工作计划：

开发工作计划：
- 对需求用例的改进项进行整理--刘威 4.30下午开始。5.5日全部整理完毕
- 液位逻辑数据测试调整--孙彪龙 5.5开始 
- 付油逻辑数据测试调整--孙彪龙 5.8开始,预计5.11完毕.待定。
- 校正图的接口调试--刘威 5.5~5.7接口调试
- 数据异常接口--刘威 5.8
- 数据导出--刘威 5.9
- 前端校正界面--杨娇 5.5~5.9
- 异常界面--杨娇 5.11
- 5.11 之后前端及后端配合测试修复bug  杨娇、刘威
- 数据的准确性完整性测试--孙彪龙 5.13之后 

- 安装盘及部署手册--刘威 5.11开始着手，21号之前完成（同时需要考虑以后的更新维护等）
- 现场部署--孙彪龙、杨娇、刘威 预计5.21之后
- 数据跟踪 

测试工作计划（以下时间是模糊推算的，具体工作时间，请测试组安排）：

5.5 业务信息了解
5.6 测试用例等

一期 5.6~5.12 
测试如有问题，可以讲解讨论
dit接收到的业务基础数据（主要是查询收到的数据，没有复杂功能）
- 罐存数据
- 付油数据
- 卸油记录
- 库存监控
- 油枪监控

功能性测试（界面的功能较多）。
- 付油确认界面
- 液位组确认
- 回罐确认
- 付油与液位组数据
- 卸油数据


二期
数据完整性测试 5.13开始
- 付油确认
- 液位组确认
- 回罐确认
- 付油与液位组数据
- 卸油数据
- 异常信息

三期
- 校正界面 待定 5.15开始