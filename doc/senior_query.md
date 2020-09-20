# ES 高级搜索

## 初始化数据

    PUT /books/_doc/1
    {"name":"老人与海 The Old Man and the Sea","year": 1952,"author":"Ernest Hemingway","info":"主人公是一位名叫圣地亚哥的老渔夫，配角是一个叫马诺林的小孩。风烛残年的老渔夫一连八十四天都没有钓到一条鱼，但他仍不肯认输，而是充满着奋斗的精神，终于在第八十五天钓到一条身长十八尺，体重一千五百磅的大马林鱼。..."}
    
    PUT /books/_doc/2
    {"name":"小王子 Little Prince","year": 1942,"author":"Antoine de Saint-Exupéry","info":"我请孩子们原谅我把这本书献给了一个大人。我有一个很重要的理由：这个大人是我在世界上最好的朋友。我还有另一个理由：这个大人他什么都能懂，甚至给孩子 们写的书他也能懂。我的第三个理由是：这个大人住在法国，他在那里挨饿、受冻。他很需要安慰。如果这些理由还不..."}
    
    PUT /books/_doc/3
    {"name":"了不起的盖茨比 The Great Gatsby","year": 1925,"author":"F. Scott Fitzgerald","info":"The Great Gatsby is a novel by the American author F. Scott Fitzgerald. First published in 1925, it is set on Long Island's North Shore and in New York City from spring to autumn of 1922. The novel ta..."}
    
    PUT /books/_doc/4
    {"name":"傲慢与偏见 Pride And prejudice","year": 1813,"author":"Jane Austen","info":"小乡绅班纳特有五个待字闺中的千金，班纳特太太整天操心着为女儿物色称心如意的丈夫。新来的邻居彬格莱是个有钱的单身汉，他立即成了班纳特太太追猎的目标。在一次舞会上，彬格莱对班纳特家的大女儿吉英一见钟情，班纳特太太为此欣喜若狂。参加舞会的还有彬格莱的好友..."}
    
    PUT /books/_doc/5
    {"name":"飘 Gone With The Wind","year": 1936,"author":"Margaret Mitchell","info":"《飘》是美国女作家玛格丽特米切尔（19001949）十年磨一剑的作品，也是唯一的作品。《飘》称得上有史以来最经典的爱情巨著之一，由费雯丽和克拉克盖博主演的影片亦成为影史上不..."}

## 搜索

### 1、query string search
> 适用于临时搜索，生产一般不用

- 1.1 搜索全部

    `GET /books/_search`

- 1.2 根据名称搜索 + 根据初版时间排序

    `GET /books/_search?q=name:The&sort=year:asc`

- 1.3 搜索结果分析

    ```json5
    {
      "took" : 6, // 耗时（毫秒）
      "timed_out" : false, // 是否超时
      "_shards" : {
        "total" : 1,
        "successful" : 1,
        "skipped" : 0,
        "failed" : 0
      },
      "hits" : {
        "total" : {
          "value" : 3, // 结果数量
          "relation" : "eq"
        },
        "max_score" : null, // 结果集中最大的相关度
        "hits" : [
          {
            "_index" : "books",
            "_type" : "_doc",
            "_id" : "1",
            "_score" : null, // 当前结果相关度
            "_source" : {
              "name" : "老人与海 The Old Man and the Sea",
              "year" : 1952,
              "author" : "Ernest Hemingway",
              "info" : "主人公是一位名叫圣地亚哥的老渔夫，配角是一个叫马诺林的小孩。风烛残年的老渔夫一连八十四天都没有钓到一条鱼，但他仍不肯认输，而是充满着奋斗的精神，终于在第八十五天钓到一条身长十八尺，体重一千五百磅的大马林鱼。..."
            },
            "sort" : [
              1952
            ]
          }
        ]
      }
    }
    ```

### 2、query DSL
> DSL(Domain Specified Language) 特定领域的语言
>
> 可以用json格式的查询语法，构建复杂的查询

- 2.1 搜索全部
```
GET /books/_search
```
```json
{
  "query": {"match_all": {}}
}
```

- 2.2 根据名称搜索 + 根据初版时间排序
```
GET /books/_search
```
```json
{
  "query": {
    "match": {
      "name": "The"
    }
  },
  "sort": [
    {
      "year": {
        "order": "desc"
      }
    }
  ]
}
```

- 2.3 分页查询
```
GET /books/_search
```
```json
{
  "query": {"match_all": {}},
  "from": 0,
  "size": 2
}
```

- 2.4 查询指定字段
```
GET /books/_search
```
```json
{
  "query": {"match_all": {}},
  "_source": ["name", "author"]
}
```

### 3、query filter
```
GET /books/_search
```
```json
{
  "query": {
    "bool": {
      "must": [
        {"match": {
          "name": "The"
        }}
      ],
      "filter": [
        {"range": {
          "year": {
            "gte": 1930,
            "lte": 1960
          }
        }}
      ]
    }
  }
}
```

### 4、full-text search（全文检索）
```
GET /books/_search
```
```json
{
  "query": {
    "match": {
      "name": "The Wind"
    }
  }
}
```

### 5、phrase search（短语搜索）
```
GET /books/_search
```
```json
{
  "query": {
    "match_phrase": {
      "name": "The Wind"
    }
  }
}
```

### 6、highlight search（高亮搜索）
```
GET /books/_search
```
```json
{
  "query": {
    "match": {
      "name": "the Wind"
    }
  },
  "highlight": {
    "fields": [{
      "name": {}
    }]
  }
}
```