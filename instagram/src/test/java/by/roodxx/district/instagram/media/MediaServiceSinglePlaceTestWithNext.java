package by.roodxx.district.instagram.media;

import by.roodxx.district.core.data.Media;
import by.roodxx.district.core.data.MediaType;
import by.roodxx.district.core.data.Owner;
import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.core.stream.MediaCursor;
import by.roodxx.district.instagram.data.GetRequest;
import by.roodxx.district.instagram.data.HttpDataFetcher;
import by.roodxx.district.instagram.data.Response;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.*;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MediaServiceSinglePlaceTestWithNext {

    private InstagramMediaService mediaService;

    private Place place;

    private Comparator<Media> comparator;

    @Before
    public void setUp() throws IOException {
        HttpDataFetcher dataFetcher = mock(HttpDataFetcher.class);
        when(dataFetcher.fetchByGet(any(GetRequest.class))).thenAnswer(new Answer<Response>() {
            private int requestCount = 0;
            @Override
            public Response answer(InvocationOnMock invocation) throws Throwable {
                String data;
                switch (requestCount) {
                    case 0: {
                        data = getDataFromFile("data/media/location_266878632_response_part1.json");
                        break;
                    }
                    case 1: {
                        data = getDataFromFile("data/media/location_266878632_response_part2.json");
                        break;
                    }
                    default: {
                        data = null;
                    }
                }
                requestCount++;
                return new Response(HttpStatus.SC_OK, data);
            }
        });

        mediaService = new InstagramMediaService(dataFetcher);

        place = new Place("266878632", "Новобелица Гомель", new Location("52.390576346667", "31.029956036667"));

        comparator = new MediaComparator();

    }

    private String getDataFromFile(String path) throws IOException {
        return IOUtils.toString(
                MediaServiceSinglePlaceTestWithoutNext.class.getClassLoader().getResourceAsStream(path), "UTF-8");
    }



    @Test
    public void testSinglePlaceWithNext() {

        MediaCursor<Media> mediaCursor = mediaService.load(Collections.singleton(place));
        assertNotNull(mediaCursor);

        assertTrue(mediaCursor.hasNext());
        checkOutput(feelOutputPart1(), mediaCursor.next());

        assertTrue(mediaCursor.hasNext());
        checkOutput(feelOutputPart2(), mediaCursor.next());
    }

    private void checkOutput(Collection<Media> output, Collection<Media> feed) {
        assertEquals(output.size(), feed.size());

        Iterator<Media> outputIterator = output.iterator();
        Iterator<Media> feedIterator = feed.iterator();

        while (outputIterator.hasNext()) {
            Media outputItem = outputIterator.next();
            Media feedItem = feedIterator.next();

            if (comparator.compare(outputItem, feedItem) != 0) {
                fail(String.format("Medias are not equal\n: Expected\n: %s \nActual\n: %s\n", outputItem, feedItem));
            }
        }
    }

    private Collection<Media> feelOutputPart1() {
        Collection<Media> output = new ArrayList<>(12);
        output.add(createMedia("1309427177003026425","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13725549_496664357193524_799949020_n.jpg?ig_cache_key=MTMwOTQyNzE3NzAwMzAyNjQyNQ%3D%3D.2",url("BIsBRRwggf5"),null, MediaType.PICTURE,1470315905L,loc(),owner("1250256194")));
        output.add(createMedia("1309371855853824049","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c0.80.640.640/13696651_1013406778776341_1852629077_n.jpg?ig_cache_key=MTMwOTM3MTg1NTg1MzgyNDA0OQ%3D%3D.2.c",url("BIr0sP7D6gx"),"\uD83D\uDC96",MediaType.PICTURE,1470309310L,loc(),owner("1502091603")));
        output.add(createMedia("1309299840853205894","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.18.1080.1080/13767684_1767929903492261_1250841549_n.jpg?ig_cache_key=MTMwOTI5OTg0MDg1MzIwNTg5NA%3D%3D.2.c",url("BIrkUSuh-OG"),"Day 4⃣\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38 for\n#LoveWithin#EcoGreen Challenge \uD83C\uDF40\nHeadstand aka Sirsasana на мосту \uD83C\uDF01\uD83D\uDE48 Честно говоря, если бы мне кто-нибудь сказал, что когда-нибудь я буду стоять на голове, я бы смеялась до слез \uD83D\uDE02 Ибо моя физ подготовка даже в школе оставляла делать лучшего \uD83D\uDE04 Спасибо челленджам за вызов - пару месяцев попыток и я стою \uD83D\uDE46\uD83C\uDFFB Больше всех, безусловно, помог муж \uD83D\uDC95\uD83D\uDEB6\uD83C\uDFFB\n\uD83C\uDF33 Hosts\n\uD83C\uDF33 @hannahtaha\n\uD83C\uDF33 @linneaslifestyle \uD83C\uDF33@silver_cloudss \uD83C\uDF33@theyogacouple\n\uD83C\uDF33 @lorana_yoga\n⭐⭐⭐⭐⭐⭐\n\uD83C\uDF40 Sponsor\n\uD83C\uDF40 #BaliniSports\n\uD83C\uDF40@balinisports\n#yogachallenge #augustyogachallenge #yoga #yogatime #йога #йогачеллендж #гомель #беларусь #йогагомель #йогабеларусь #gomel #yogagomel #belarus #yogabelarus #summer #йоганаприроде #белица #белицкиеозера #ширшасана #стойканаголове #sirsasana #headstand #bridge #мост",MediaType.PICTURE,1470300725L,loc(),owner("477209222")));
        output.add(createMedia("1309297728820174994","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.99.1080.1080/13732252_1250943088252134_1984189507_n.jpg?ig_cache_key=MTMwOTI5NzcyODgyMDE3NDk5NA%3D%3D.2.c",url("BIrj1jvjWCS"),"Делай что хочешь : \nГуляй до утра, пей вино,носи розовые кроксы\uD83D\uDE02\n#vscocam#life #Belarus #gomel#night",MediaType.PICTURE,1470300473L,loc(),owner("454325730")));
        output.add(createMedia("1309292602340561830","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13707178_200990286970743_404417478_n.jpg?ig_cache_key=MTMwOTI5MjYwMjM0MDU2MTgzMA%3D%3D.2",url("BIriq9VjlOm"),null,MediaType.PICTURE,1470299862L,loc(),owner("1159278657")));
        output.add(createMedia("1309246993578634594","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13739494_157860561309445_389669437_n.jpg?ig_cache_key=MTMwOTI0Njk5MzU3ODYzNDU5NA%3D%3D.2",url("BIrYTQ3gK1i"),"Эй, ю! Пришло время скучать\nНо скучания не для нас, ведь правда?\uD83D\uDE0F",MediaType.PICTURE,1470294425L,loc(),owner("1425062280")));
        output.add(createMedia("1309240887594738937","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13696984_1652663755049461_1043795591_n.jpg?ig_cache_key=MTMwOTI0MDg4NzU5NDczODkzNw%3D%3D.2",url("BIrW6aOgGT5"),"\uD83D\uDE48\uD83D\uDE4A✌",MediaType.PICTURE,1470293697L,loc(),owner("2728045665")));
        output.add(createMedia("1309213069142680461","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c0.80.640.640/13694779_123046701471193_1521575535_n.jpg?ig_cache_key=MTMwOTIxMzA2OTE0MjY4MDQ2MQ%3D%3D.2.c",url("BIrQlmRjGON"),"Доброе предоброе утро всем)#всемхорошегонастроения #мирвашемудому",MediaType.PICTURE,1470290381L,loc(),owner("2076978523")));
        output.add(createMedia("1308991135196184441","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13736047_148860798882941_1633125484_n.jpg?ig_cache_key=MTMwODk5MTEzNTE5NjE4NDQ0MQ%3D%3D.2",url("BIqeICLgP95"),"#Repost @rita_nexina with @repostapp\n・・・\nПоздравляю с поступлением @safronitt1212 #фотоизархива\uD83D\uDCF7\nРитусс, спасибо большое!!",MediaType.PICTURE,1470263924L,loc(),owner("1496091187")));
        output.add(createMedia("1308963520939582121","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c236.0.608.608/13722144_1100387213389026_1483625462_n.jpg?ig_cache_key=MTMwODk2MzUyMDkzOTU4MjEyMQ%3D%3D.2.c",url("BIqX2MZjGKp"),"Отвезите мою жопку на море \uD83D\uDE39",MediaType.PICTURE,1470260633L,loc(),owner("1290158778")));
        output.add(createMedia("1308961149622214707","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c135.0.810.810/13734453_132313643875878_524795081_n.jpg?ig_cache_key=MTMwODk2MTE0OTYyMjIxNDcwNw%3D%3D.2.c",url("BIqXTr8BOAz"),"Day 4⃣ \uD83C\uDF88\uD83C\uDF88\uD83C\uDF88\nSwan Pose aka Eka Pada Rajakaporasana light variation \uD83C\uDF43\uD83D\uDC51\uD83D\uDC26 for\n#TheHeartOfPractice ❤️\n@beachyogagirl \n@liforme \n@omstarsapparel \n#yogachallenge #augustyogachallenge #yoga #yogatime #йога #йогачеллендж #гомель #беларусь #йогагомель #йогабеларусь #gomel #yogagomel #belarus #yogabelarus #summer #йоганаприроде #позакоролевскогоголубя #позаголубя #экападараджакапотасана #swanpose #ekapadarajakapotasana #pigeonpose",MediaType.PICTURE,1470260350L,loc(),owner("477209222")));
        output.add(createMedia("1308954209995238102","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13767635_278524062525549_884980796_n.jpg?ig_cache_key=MTMwODk1NDIwOTk5NTIzODEwMg%3D%3D.2",url("BIqVus6BKrW"),"Поздравляю с поступлением @safronitt1212 #фотоизархива\uD83D\uDCF7",MediaType.PICTURE,1470259523L,loc(),owner("1952027054")));
        return output;
    }

    private Collection<Media> feelOutputPart2() {
        Collection<Media> output = new ArrayList<>(12);
        output.add(createMedia("1308910004162415417","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c100.0.600.600/13707448_1758624581093551_1667490783_n.jpg?ig_cache_key=MTMwODkxMDAwNDE2MjQxNTQxNw%3D%3D.2.c","instagram.com/p/BIqLrbBDks5",null,MediaType.PICTURE,1470254253L,loc(),owner("3081416569")));
        output.add(createMedia("1308895780723969078","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13696592_102357810211631_1080699115_n.jpg?ig_cache_key=MTMwODg5NTc4MDcyMzk2OTA3OA%3D%3D.2","instagram.com/p/BIqIccaD0w2","Моя кошка всегда пытается влезть в каждую фотку ✌✊\n#follows #followshoutoutlikecomment #follow #followme #followme #followforfollow #follow4follow #followforfollow #likebackteam #like4like #life #likeforlike #like #likesforlikes",MediaType.PICTURE,1470252557L,loc(),owner("1622131517")));
        output.add(createMedia("1308843788114706349","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13734411_1128470833881178_2021410503_n.jpg?ig_cache_key=MTMwODg0Mzc4ODExNDcwNjM0OQ%3D%3D.2","instagram.com/p/BIp8n2hDb-t",null,MediaType.PICTURE,1470246359L,loc(),owner("3620253259")));
        output.add(createMedia("1308834476029291830","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c236.0.608.608/13744213_1755965267995419_1041607466_n.jpg?ig_cache_key=MTMwODgzNDQ3NjAyOTI5MTgzMA%3D%3D.2.c","instagram.com/p/BIp6gV9gq02","Наконец-то я добралась до него..\n#mebeforeyou#довстречистобой#будетмногослёз#исоплей#чувствую",MediaType.PICTURE,1470245249L,loc(),owner("1761869859")));
        output.add(createMedia("1308829965952864159","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c75.0.453.453/13774639_178429372576690_399323069_n.jpg?ig_cache_key=MTMwODgyOTk2NTk1Mjg2NDE1OQ%3D%3D.2.c","instagram.com/p/BIp5etoAc-f","Уедешь, можешь не возвращаться",MediaType.PICTURE,1470244712L,loc(),owner("1496091187")));
        output.add(createMedia("1308828383005741828","https://scontent-waw1-1.cdninstagram.com/l/t51.2885-15/e35/c75.0.453.453/13706873_1570683719900819_1497742595_n.jpg?ig_cache_key=MTMwODgyODM4MzAwNTc0MTgyOA%3D%3D.2.c","instagram.com/p/BIp5HrZAZME","В распоряжении вся ночь",MediaType.PICTURE,1470244523L,loc(),owner("1496091187")));
        output.add(createMedia("1308805083714121515","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c82.0.440.440/13774193_156282074801964_1310894501_n.jpg?ig_cache_key=MTMwODgwNTA4MzcxNDEyMTUxNQ%3D%3D.2.c","instagram.com/p/BIpz0oPAW8r","И это моя вечеринка",MediaType.PICTURE,1470241745L,loc(),owner("1496091187")));
        output.add(createMedia("1308779474057180751","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c0.56.451.451/13774653_652550028235500_1574005365_n.jpg?ig_cache_key=MTMwODc3OTQ3NDA1NzE4MDc1MQ%3D%3D.2.c","instagram.com/p/BIpt_9YgDZP","Прошло три дня, а я уже ОБОЖАЮ СВОЮ РАБОТУ! Вот уже и пропуск сделали..\nА еще я поступил в УНИВЕРСИТЕТ! \nВсем спасибо ,все свободны",MediaType.PICTURE,1470238693L,loc(),owner("1496091187")));
        output.add(createMedia("1308772307988123544","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c41.0.522.522/13735965_777657962375760_1463850177_n.jpg?ig_cache_key=MTMwODc3MjMwNzk4ODEyMzU0NA%3D%3D.2.c","instagram.com/p/BIpsXrdgA-Y","Урвали от мамки еду богов",MediaType.PICTURE,1470237838L,loc(),owner("1496091187")));
        output.add(createMedia("1308759640863803848","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e15/c80.0.480.480/13767653_301293516889758_1388326043_n.jpg?ig_cache_key=MTMwODc1OTY0MDg2MzgwMzg0OA%3D%3D.2.c","instagram.com/p/BIppfWSDgnI","Нечего делать",MediaType.VIDEO,1470236328L,loc(),owner("3114873228")));
        output.add(createMedia("1308745651718991040","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/13712614_1621733354823400_870964471_n.jpg?ig_cache_key=MTMwODc0NTY1MTcxODk5MTA0MA%3D%3D.2","instagram.com/p/BIpmTx4DCzA","Природа с 📸: @litvinnnn 🍃🌾🌿",MediaType.PICTURE,1470234661L,loc(),owner("290560768")));
        output.add(createMedia("1308716506942680837","https://scontent-waw1-1.cdninstagram.com/t51.2885-15/e35/c0.56.453.453/13712754_1754064244879556_466979140_n.jpg?ig_cache_key=MTMwODcxNjUwNjk0MjY4MDgzNw%3D%3D.2.c","instagram.com/p/BIpfrqsAi8F","Еще вечер и #покапока",MediaType.PICTURE,1470231186L,loc(),owner("1496091187")));

        return output;
    }

    private Location loc() {
        return new Location("52.390576346667", "31.029956036667");
    }

    private Owner owner(String id) {
        return new Owner(id, "instagram");
    }

    private String url(String code) {
        return "instagram.com/p/"+code;
    }

    private Media createMedia(String id, String mediaSource, String mediaUrl, String description, MediaType mediaType,
                              long timestamp, Location location, Owner owner) {
        Media media = new Media(mediaSource, location);
        media.setMediaUrl(mediaUrl);
        media.setId(id);
        media.setOwner(owner);
        media.setDescription(description);
        media.setTimestamp(timestamp);
        media.setMediaType(mediaType);
        return media;
    }
}
