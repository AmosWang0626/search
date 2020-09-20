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

4. Type（elasticsearch 8.0 将去掉，后边忽略）

    类型，每个索引里都可以有一个或多个type，是index中的一个逻辑数据分类

5. 分布式相关

   - Node 节点
   - Cluster 多个相同集群名称的节点集合
   - Shard 分片，索引中的数据可以分为多个分片，分布在多台服务器上，便于横向扩展，提升吞吐量和性能
   - Replica 副本，可以为每个分片创建多个副本，用于灾备或提升搜索的吞吐量和性能

6. 倒排索引

    ES的核心之一就是Lucene倒排索引。
    就像你往ES中保存了一首古诗，后边立马就能通过古诗中的部分内容搜到这首古诗，这就是倒排索引的应用场景。

### 与关系型数据库对比

|   | ES       | 关系型数据库 |
|---| ---      | ---   |
| 1 | Index    | 表    |
| 2 | Mapping  | 表结构 |
| 3 | Document | 数据行 |

### 基本命令（elasticsearch-7.9.1）

1. 查看集群健康状态 `GET /_cat/health?v`
2. 查看所有索引 `GET /_cat/indices?v`
3. 创建索引 `PUT /index_test01?pretty`
4. 删除索引 `DELETE /index_test01`
5. 创建文档（索引不存在会自动创建）
    ```
    PUT /doc_test/_doc/prd01
    {
      "name": "Journey Under the Midnight Sun",
      "type": "book",
      "info": "this is a book!"
    }
    ```
6. 查询文档
    ```
    GET /doc_test/_doc/prd01
    ```
7. 修改文档
    ```
    POST /doc_test/_update/prd01/
    {
      "doc": {
        "info": "this is a book!",
        "author": "Higashino Keigo"
      }
    }
    ```
8. 删除文档
    ```
    DELETE /doc_test/_doc/prd01
    ```

### 高级查询
[Senior Query](doc/senior_query.md) 