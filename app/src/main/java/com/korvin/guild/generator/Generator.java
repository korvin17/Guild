package com.korvin.guild.generator;

import com.korvin.guild.R;
import com.korvin.guild.event.town.Place;

/**
 * Created by Korvin on 03.05.2015.
 */
public class Generator {

    public static Place generateTown() {
        Place place_1 = new Place("shutter-gate\n" +
                "torch-fire on a stick\n" +
                "precious-valuable\n" +
                " car jack-tools for change tyre\n" +
                "grille-grid\n" +
                "sense-meaning\n" +
                "\n" +
                "Where were you  yesterday? I was at home the whole day. How strange. I rang you up at two o’clock but nobody answered. Oh I was in the garden. I was reading your book and didn’t hear the telephone.\n" +
                "What were you doing at five o’clock yesterday? I was working in the library. i was there, too but didn’t see you.\n" +
                "Nina celebrated her birthday yesterday. Her room looked beautiful, there were many flowers in it. When I came in, somebody was playing the piano, two or three pairs were dancing. Listen! Somebody is playing the piano. \n" +
                "I like music very much.\n" +
                "When I looked out of the window, it was raining heavily and people were hurrying along the street. \n" +
                "What were you doing at seven o’clock yesterday. I had supper. \n" +
                "When I came home yesterday I saw  that all my family were sitting round the table. Father was readying a letter from my uncle, who live in Kiev. \n" +
                "Yesterday I was working at my English from five till seven. It was raining the whole day yesterday. Where is your sister now? She is in her room. She is doing her homework. \n" +
                "He is brushing his teeth at the moment. He cleans them thoroughly morning and nights. \n" +
                "Don’t disturb her while she is sleeping. You talk nonsense. You never talk sense. My mother is sitting in the sunlight now and I setting the table. \n" +
                "\f\n" +
                "scarf - clothes for neck.\n" +
                "pyjamas-clothes for sleep\n" +
                "trousers - for foot\n" +
                "tights- woman for foot\n" +
                "iron- tool for press a clother\n" +
                "swan - beautiful duck\n" +
                "Authority-government\n" +
                "ferry-ship\n" +
                "property-things\n" +
                "\n" +
                "Yesterday was a common working day for me. I woke up early morning. I had two sandwich and a cup of tea. Then I went at the work. While I was sitting in a train, I heard there chapters of an audio book. I heard the book two times and changed the train with underground. I wished some rest, so I decided to read news. I didn’t remember any news, because they all were boring. \n" +
                "When I arrived at the work, only one colleague was working. Usually I come the third. but this week a colleague has a holiday. We like to start from seven, because nobody else don’t come so early. The work as programmers needs concentration and It isn’t possible to work while other people ask you question. From eight till twelve we were working hard. We did (have done) a lot of work and had diner. After diner usually we only have meetings and fix bug.\n" +
                "After work I didn’t go direct home. I had to visit my car school again. I had to enroll, but I wasn’t successful. The queue was full. I was very sad, because it was the second attempt and I had to do another one. \n" +
                "Only later evening I came back. I rest half o’clock in my bedroom. I drank a cup of juice. And I went outside of the house. Within an hour I ran across a small forest. After exercise I came back and took a shower. At nights I spend little time at chatting with my friend and went sleep. ", R.drawable.place_forest_1);
        Place place_2 = new Place("лес", R.drawable.place_forest_2);
        Place place_3 = new Place("лес", R.drawable.place_forest_3);
        place_1.addWay("дорожка вниз", place_2);
        place_2.addWay("дорожка вверх", place_1);
        place_2.addWay("подвал", place_3);
        place_3.addWay("подвал", place_1);
        return place_1;
    }
}
