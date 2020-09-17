# Search
> 一见如故

## 起步
> 安装就不说了，傻瓜式安装

### 基本概念
1. Near Realtime (NRT)

    近实时，从创建索引到能搜索，延时一般仅1秒

2. Document 

    文档，es中的最小数据单元，通常用 JSON 数据结构标示

3. Index

    索引，相同数据结构的文档集合

4. Type

    类型，每个索引里都可以有一个或多个type，是index中的一个逻辑数据分类

5. 分布式相关
  - Node 节点
  - Cluster 多个相同集群名称的节点集合
  - Shard 分片，索引中的数据可以分为多个分片，分布在多台服务器上，便于横向扩展，提升吞吐量和性能
  - Replica 副本，可以为每个分片创建多个副本，用于灾备或提升搜索的吞吐量和性能

### 与关系型数据库对比

|   | Elastic Search | 关系型数据库 |
|---| ---      | ---   |
| 1 | Index    | 数据库 |
| 2 | Type     | 表    |
| 3 | Document | 行    |