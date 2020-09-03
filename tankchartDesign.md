#1.概述
 >>此文档为设计文档及需要实现的功能列表资源安排,此项目主要包含以下包路径及其主要功能；

  ##1.1.包路径概述com.oket下各一级包说明
| 包名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|station|加油站实体类，主要包括加油站地址等||
|tank4station|加油站油罐类相关实体类及其相关业务||
|dispenser|加油机及加油枪相关实体及业务类||
|tankchartdc|油罐容积表诊断及标定相关业务类||
|dispenser|卸油相关业务操作||

# 2.详细说明
## 2.1.油罐com.oket.tank4station 
   接口

| 接口名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|LevelTrace|主要定义液位轨迹相关接口|实现类AbstactorLevelTrace|
|TankLevel|定义液位相关接口|实现类LevelInfo|
|TankInventory|定义油罐实时库存接口|
   |TankSession|定义油罐一个付油周期（包含其卸油、回罐液位组）|第一次（或者设备故障）会没有卸油液位组
   油罐相关实体类

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|Levelinfo|液位相关信息，不包括液位对应的体积||
|Inventory|LevelInfo的子类，包含对应液位的体积|此体积数是原有容积表计算所得|
|LevelState|液位的状态，枚举量|稳定、上升、下降、波动，初始未知|
|DbInventory|液位实体||
|DbInventoryAlarm|液位报警实体|液位过长时间没有上传会有报警|
|DbInventoryCycle|液位周期实体|有富有周期和鞋油周期|
|DbInventoryLast|最后一笔液位|每个罐只有一条,方便查询|
|DbInventoryTrace|液位轨迹实体|一组液位,包含开始和结束液位|
|UpdateTableInfo|容积表实体|保存油罐的容积表|

#### service相关

| 类名               | 主要功能     | 备注 |
| ------------ | ------------ | ---- |
| InventoryService | 处理液位数据.是处理数据的入口. | dit接收到数据的时候,会在这个处理接收到的液位数据 |
| AbstractLevelTrace | 抽象液位处理.提供一些共有的液位轨迹的处理方案 |      |
| DownInventoryState | 下降液位(付油)处理.以及付油周期的判断 |      |
| SecretInventoryState | 初始未知状态处理 |      |
| StableInventoryState | 稳定液位处理 |      |
| UpInventoryState | 上升液位处理.包含卸油周期的判断 |      |
| DbInventoryAlarmService | 液位报警处理(目前仅有过长时间未接收到数据的报警) |      |
| DbInventoryCycleService | 液位周期处理,以及增删改查相关接口 |      |
| DbInventoryTraceService | 液位轨迹的增删改查相关接口 | |
| VolumeTableService | 容积表的增加和保存 | |
| DbInventoryService | 液位增删改查相关接口 | |



## 2.2.油站com.oket.station 
 加油站相关实体类

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|UserEntity|油站用户相关信息|
|StationEntity|油站相关信息|

加油站相关接口

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|UserController|油站用户Controller:登录、注册、修改账号功能|
|UserService|油站用户Service:登录、注册、修改账号功能|
|UserDao|油站用户Dao:登录、注册、修改账号功能|
|StationController|油站Controller:查询油站所有信息、修改油站信息功能|
|StationService|油站Service:查询油站所有信息、修改油站信息功能|
|StationDao|油站Dao:登录、注册、修改账号功能|

##2.3.dit模拟器com.oket.tankchartdc 

 dit相关实体类

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|DitEntity|json和ifsf端口号信息|

 dit相关接口

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|DitController|dit模拟器Controller:查询当前dit端口、修改当前dit端口、开启和关闭dit功能|
|DitService|dit模拟器Service:查询当前dit端口、修改当前dit端口、开启和关闭dit、查询Json和Ifsf的端口号功能|
|DitDao|dit模拟器Dao:查询当前dit端口、修改当前dit端口、开启和关闭dit、查询Json和Ifsf的端口号功能|

##2.4.设备com.oket.device  

 设备相关实体类

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|NozRelDisServiceEntity|油枪加油机关系|
|NozTankRelationEntity|枪罐关系|


 设备相关接口

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|NozRelDisController|油枪加油机关系接口:查询更改解除关系功能|
|NozTankRelationController|枪罐关系接口:查询更改解除关系功能|



##2.5.卸油相关com.oket.delivery 

 卸油相关实体类

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|DeliveryConfirm|与数据库相关的卸油确认实体|
|Confirm|返回给前端的卸油确认实体|

 卸油相关接口

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|DeliveryController|卸油查询接口:查询卸油列表以及油品功能|
|DeliveryConfirmController|卸油确认接口:查询及确认卸油记录|



设备相关接口

| 类名 | 主要功能 |  备注 |
|:--------:| :-------- | -----:|
|NozRelDisController|油枪加油机关系Controller:|
|NozRelDisService|油枪加油机关系Service:|
|NozRelDisDao|油枪加油机关系Dao:|
|NozTankRelationController|枪罐关系Controller:|
|NozTankRelationService|枪罐关系Service:|
|NozTankRelationDao|枪罐关系Dao:|

## 2.3 付油相关com.oket.dispenser

### 相关的实体

| 类名               | 主要功能                                        | 备注                             |
| ------------------ | ----------------------------------------------- | -------------------------------- |
| BzNozzleOut        | 付油数据                                        | dit推送过来,会保存到数据库       |
| BzNozzleOutGroup   | 付油组                                          | 一到多笔付油数据组成一个付油组   |
| BzNozzleOutLast    | 最后一笔付油数据                                | 方便查询最后一比加油             |
| BzTraceRelOutGroup | 液位轨迹和付油组关联关系                        |                                  |
| DispenserErrorItem | 油枪精度                                        |                                  |
| IFSFNozzleState    | 油枪状态实体.包含油枪当前状态信息和付油中实体组 | 用于判断当前油枪状态处于那种状态 |
| NozzleOutType      | 付油类型                                        |                                  |
| NozzleState        | 油枪的状态                                      | ifsf协议中定义的几种状态         |

### 处理相关的service

| 类名                      | 主要功能                                           | 备注                      |
| ------------------------- | -------------------------------------------------- | ------------------------- |
| NozzleService             | 处理付油数据的入口.                                | dit推送的数据会在这里处理 |
| NozzleOutService          | 付油数据处理.  增删改查操作                        |                           |
| DispenserErrorItemService | 油枪精度的增删改查相关接口                         |                           |
| BzTraceRelOutGroupService | 处理液位轨迹组构成关联关系.提供增删改查接口,       |                           |
| BzOutRelGroupService      | 处理付油数据和付油组的关联关系                     |                           |
| BzNozzleOutGroupService   | 处理付油数据,形成付油组,并和液位轨迹组构成关联关系 |                           |

##2.  导出数据格式

    
}



# 附录

## A资源安排
A.1.[AbstractLevelTrace](./src/main/java/com/oket/tank4station/AbstractLevelTrace.java)类  
资源与时间 刘威 2020-03-30~2020-04-03 完成以下方法

+ public void merge(List<TankLevel> srcList,boolean isRemove);
+ public void merge(List<TankLevel> srcList, boolean isRemove, int startIndex) 
+ public void merge(List<TankLevel> srcList, boolean isRemove, int startIndex, int indexEnd)

