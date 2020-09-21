# ES 高级搜索

## 初始化数据

    PUT /computer/_doc/S2
    {"name":"ThinkPad S2","model":"20R7A018CD","processor":"i5-10210U","memory":"16G","hardDisk":"512G","screenSize":"13.3英寸","price":5499.00,"description":null}
    PUT /computer/_doc/14
    {"name":"ThinkBook 14","model":"20SLA028CD","processor":"i7-1065G7","memory":"8G","hardDisk":"512G","screenSize":"14英寸","price":5699.00,"description":null}
    PUT /computer/_doc/X13
    {"name":"ThinkPad X13","model":"20T2A005CD","processor":"i7-10510U","memory":"8G","hardDisk":"512G","screenSize":"13.3英寸","price":7999.00,"description":null}
    PUT /computer/_doc/T14
    {"name":"ThinkPad T14","model":"20S0A007CD","processor":"i5-10210U","memory":"16G","hardDisk":"512G","screenSize":"14英寸","price":8999.00,"description":null}
    PUT /computer/_doc/P15s
    {"name":"ThinkPad P15s","model":"20T4A000CD","processor":"i7-10510U","memory":"16G","hardDisk":"512G","screenSize":"15.6英寸","price":10499.00,"description":null}
    PUT /computer/_doc/X1
    {"name":"ThinkPad X1","model":"20U90039CD","processor":"i7-10710U","memory":"16G","hardDisk":"1T","screenSize":"14英寸","price":16999.00,"description":null}

## 搜索

### 1、query string search
> 适用于临时搜索，生产一般不用

- 1.1 搜索全部

    `GET /computer/_search`

- 1.2 根据名称搜索 and 价格排序

    `GET /computer/_search?q=name:ThinkPad&sort=price:desc`

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
            "_index" : "computer",
            "_type" : "_doc",
            "_id" : "X1",
            "_score" : null,
            "_source" : {
              "name" : "ThinkPad X1",
              "model" : "20U90039CD",
              "processor" : "i7-10710U",
              "memory" : "16G",
              "hardDisk" : "1T",
              "screenSize" : "14英寸",
              "price" : 16999.0,
              "description" : null
            },
            "sort" : [
              16999.0
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
GET /computer/_search
```
```json
{
  "query": {
    "match_all": {}
  }
}
```

- 2.2 根据名称搜索 and 价格排序
```
GET /computer/_search
```
```json
{
  "query": {
    "match": {
      "name": "ThinkPad"
    }
  },
  "sort": [
    {
      "price": {
        "order": "desc"
      }
    }
  ]
}
```

- 2.3 分页查询
```
GET /computer/_search
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
GET /computer/_search
```
```json
{
 "query": {"match_all": {}},
  "_source": ["name", "processor"]
}
```

### 3、query filter
```
GET /computer/_search
```
```json
{
  "query": {
    "bool": {
      "must": [
        {"match": {
          "processor": "i7"
        }}
      ],
      "filter": [
        {"range": {
          "price": {
            "gte": 5000,
            "lte": 10000
          }
        }}
      ]
    }
  }
}
```

### 4、full-text search（全文检索）
```
GET /computer/_search
```
```json
{
 "query": {
   "match": {
     "name": "X1"
   }
 }
}
```

### 5、phrase search（短语搜索）
```
GET /computer/_search
```
```json
{
 "query": {
   "match_phrase": {
     "name": "ThinkPad X13"
   }
 }
}
```

### 6、highlight search（高亮搜索）
```
GET /computer/_search
```
```json
{
 "query": {
   "match": {
     "name": "ThinkPad 14"
   }
 },
 "highlight": {
   "fields": {
     "name": {}
   }
 }
}
```

## 聚合分析

### 1、分组
> group by memory
```
# 默认情况下 text 类型的字段 fielddata 被禁用，如果要用于聚合分析，需要打开 fielddata
PUT /computer/_mapping
{
  "properties": {
    "memory": {
      "type": "text",
      "fielddata": true
    }
  }
}

# 执行 memory 分组
GET /computer/_search
{
  "size": 0, 
  "aggs": {
    "group_by_memory": {
      "terms": {
        "field": "memory"
      }
    }
  }
}
```

### 2、分组，根据名字筛选
```
GET /computer/_search 
{
  "size": 0,
  "query": {
    "match": {
      "name": "ThinkBook"
    }
  }, 
  "aggs": {
    "group_by_memory": {
      "terms": {
        "field": "memory"
      }
    }
  }
}
```

### 3、分组，计算平均价格
```
GET /computer/_search 
{
  "size": 0, 
  "aggs": {
    "group_by_memory": {
      "terms": {
        "field": "memory"
      },
      "aggs": {
        "avg_price": {
          "avg": {
            "field": "price"
          }
        }
      }
    }
  }
}
```

### 4、分组，计算平均价格，根据平均价格升序排序
```
GET /computer/_search 
{
  "size": 0, 
  "aggs": {
    "group_by_memory": {
      "terms": {
        "field": "memory",
        "order": {
          "avg_price": "asc"
        }
      },
      "aggs": {
        "avg_price": {
          "avg": {
            "field": "price"
          }
        }
      }
    }
  }
}
```

### 5、根据价格区间，再分组
```
GET /computer/_search
{
  "size": 0,
  "aggs": {
    "group_by_price": {
      "range": {
        "field": "price",
        "ranges": [
          {
            "from": 0,
            "to": 4999
          },
          {
            "from": 5000,
            "to": 9999
          },
          {
            "from": 10000,
            "to": 20000
          }
        ]
      },
      "aggs": {
        "group_by_memory": {
          "terms": {
            "field": "memory"
          }
        }
      }
    }
  }
}
```
