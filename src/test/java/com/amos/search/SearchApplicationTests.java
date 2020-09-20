package com.amos.search;

import com.amos.search.utils.JSON;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchApplicationTests {

    @Test
    void contextLoads() {
        Book book = new Book().setName("老人与海 The Old Man and the Sea").setAuthor("Ernest Hemingway").setInfo("主人公是一位名叫圣地亚哥的老渔夫，配角是一个叫马诺林的小孩。风烛残年的老渔夫一连八十四天都没有钓到一条鱼，但他仍不肯认输，而是充满着奋斗的精神，终于在第八十五天钓到一条身长十八尺，体重一千五百磅的大马林鱼。...");
        System.out.println(JSON.toJSONString(book));
        book = new Book().setName("小王子 Little Prince").setAuthor("Antoine de Saint-Exupéry").setInfo("我请孩子们原谅我把这本书献给了一个大人。我有一个很重要的理由：这个大人是我在世界上最好的朋友。我还有另一个理由：这个大人他什么都能懂，甚至给孩子 们写的书他也能懂。我的第三个理由是：这个大人住在法国，他在那里挨饿、受冻。他很需要安慰。如果这些理由还不...");
        System.out.println(JSON.toJSONString(book));
        book = new Book().setName("了不起的盖茨比 The Great Gatsby").setAuthor("F. Scott Fitzgerald").setInfo("The Great Gatsby is a novel by the American author F. Scott Fitzgerald. First published in 1925, it is set on Long Island's North Shore and in New York City from spring to autumn of 1922. The novel ta...");
        System.out.println(JSON.toJSONString(book));
        book = new Book().setName("傲慢与偏见 Pride And prejudice").setAuthor("Jane Austen").setInfo("小乡绅班纳特有五个待字闺中的千金，班纳特太太整天操心着为女儿物色称心如意的丈夫。新来的邻居彬格莱是个有钱的单身汉，他立即成了班纳特太太追猎的目标。在一次舞会上，彬格莱对班纳特家的大女儿吉英一见钟情，班纳特太太为此欣喜若狂。参加舞会的还有彬格莱的好友...");
        System.out.println(JSON.toJSONString(book));
        book = new Book().setName("飘 Gone With The Wind").setAuthor("Margaret Mitchell").setInfo("《飘》是美国女作家玛格丽特米切尔（19001949）十年磨一剑的作品，也是惟一的作品。《飘》称得上有史以来最经典的爱情巨著之一，由费雯丽和克拉克盖博主演的影片亦成为影史上不...");
        System.out.println(JSON.toJSONString(book));
    }

    @Data
    @Accessors(chain = true)
    private static class Book {
        private String name;
        private String author;
        private String info;
    }

}
