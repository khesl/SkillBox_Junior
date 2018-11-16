package module_7;

import module_7.TextAnalyzer.src.TextAnalyzer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TextAnalizerTester {
    TextAnalyzer textAnalyzer = null;
    private final String PATH = "S:\\my folder\\programming\\IntelliJ_IDEAProjects\\JavaExperiments\\SkillBox_Junior\\module_7\\TextAnalyzer\\res\\text_01.txt";

    public TextAnalyzer getTextAnalyzer(){
        if (textAnalyzer == null) {
            try {
                //Reading file to the String
                String text = null;
                text = new String(Files.readAllBytes(Paths.get(PATH)));
                return new TextAnalyzer(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return textAnalyzer;
    }

    @Test
    public void getFile_1(){
        String text = null;
        try {
             text = new String(Files.readAllBytes(Paths.get(PATH)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(text);
    }
    @Test
    public void getFile_2(){
        File file = new File(PATH);

        Assert.assertTrue(file.length() > 0);
    }

    @Test
    public void getWordsTest_1(){
        TextAnalyzer textAnalyzer = getTextAnalyzer();

        ArrayList<String> actualValue = textAnalyzer.getWords();
        ArrayList<String> expectedValue = new ArrayList<>();

        Assert.assertNotNull(actualValue);
    }
    @Test
    public void getWordsTest_2(){
        TextAnalyzer textAnalyzer = getTextAnalyzer();

        ArrayList<String> actualValue = textAnalyzer.getWords();
        ArrayList<String> expectedValue = new ArrayList<>();

        Assert.assertTrue(actualValue.size() > 0);
    }
    @Test
    public void getWordsTest_3(){
        TextAnalyzer textAnalyzer = getTextAnalyzer();

        String actualValue = textAnalyzer.getWords().toString();
        String expectedValue = "[japan, has, restarted, its, first, nuclear, reactor, under, new, safety, rules, following, the, fukushima, disaster, all, japan's, nuclear, plants, were, gradually, shut, down, after, a, series, of, meltdowns, at, the, fukushima, plant, sparked, by, the, tsunami, and, earthquake, but, after, passing, stringent, new, safety, tests, kyushu, electric, power, restarted, the, number, one, reactor, at, its, sendai, plant, on, tuesday, morning, there, is, still, strong, public, unease, about, a, return, to, nuclear, power, protests, have, been, taking, place, outside, the, sendai, plant, and, at, prime, minister, shinzo, abe's, residence, in, tokyo, about, km, miles, away, the, bbc's, rupert, wingfield-hayes, in, tokyo, says, that, after, being, told, a, disaster, like, fukushima, could, never, happen, public, confidence, has, been, shaken, a, total, of, plants, have, applied, to, be, restarted, he, says, but, all, are, facing, legal, challenges, from, concerned, locals, 'safety, first', kyushu, said, reactor, no, at, sendai, began, operating, again, at, local, time, gmt, reactors, and, at, sendai, nuclear, plant, japan, july, aug, the, plant, is, expected, to, be, producing, energy, by, the, end, of, the, week, tv, images, showed, the, plant's, control, room, as, workers, turned, the, reactor, back, on, kyushu, electric, spokesman, tomomitsu, sakata, said, the, reactor, had, gone, back, online, without, any, problems, it, will, be, about, hours, before, a, full, reaction, takes, place, and, the, plant, is, expected, to, start, generating, power, by, friday, it, will, reach, full, capacity, some, time, next, month, prime, minister, abe, said, on, monday, that, the, reactors, had, passed, the, world's, toughest, safety, screening, i, would, like, kyushu, electric, to, put, safety, first, and, take, utmost, precautions, for, the, restart, he, said, protesters, outside, sendai, plant, aug, many, local, residents, are, unhappy, about, the, sendai, plant, restarting, since, shutting, down, all, nuclear, plants, japan, has, been, relying, on, imported, fossil, fuels, for, its, energy, at, huge, expense, the, government, has, said, nuclear, power, must, resume, to, cut, both, import, bills, and, growing, co2, emissions, experts, have, also, warned, that, reactors, left, idle, for, years, tend, to, experience, teething, problems, and, that, such, a, mass, restart, of, dormant, reactors, has, never, been, attempted, before, says, our, correspondent, japan's, nuclear, regulation, authority, approved, two, reactors, at, the, sendai, plant, last, september, under, stricter, safety, rules, the, second, reactor, is, due, to, be, restarted, in, october, more, than, 100m, 64m, has, been, spent, on, fitting, new, safety, systems, at, the, sendai, plant, scene, after, explosion, at, the, fukushima, daiichi, plant, march, the, fukushima, daiichi, plant, was, badly, damaged, by, the, earthquake, and, tsunami, but, local, residents, say, the, new, safety, regulations, are, not, stringent, enough, they, are, worried, about, potential, dangers, from, active, volcanoes, in, the, region, protesters, were, rallied, by, naoto, kan, prime, minister, at, the, time, of, the, fukushima, crisis, who, told, the, crowd, we, don't, need, nuclear, plants, he, said, the, fukushima, disaster, had, exposed, the, myth, of, safe, and, cheap, nuclear, power, which, turned, out, to, be, dangerous, and, expensive, jump, media, playermedia, player, helpout, of, media, player, press, enter, to, return, or, tab, to, continue, media, caption, the, robot, has, produced, the, first, video, from, inside, the, reactor, as, rupert, wingfield-hayes, reports, one, of, the, most, powerful, earthquakes, ever, recorded, struck, off, the, coast, of, japan, in, march, triggering, a, huge, tsunami, which, damaged, the, fukushima, nuclear, plant, leading, to, meltdown, almost, people, died, and, more, than, are, still, listed, as, missing, though, none, of, the, deaths, has, been, linked, to, the, nuclear, disaster, some, people, were, evacuated, from, the, surrounding, areas, in, the, following, weeks, continuing, high, radiation, levels, mean, most, have, never, been, able, to, return, home]";

        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void getMostFrequentWordTest_1(){
        TextAnalyzer textAnalyzer = getTextAnalyzer();

        String actualValue = textAnalyzer.getMostFrequentWord();
        String expectedValue = "the";
        Assert.assertEquals(actualValue, expectedValue);
        Assert.assertNotNull(actualValue);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getMostFrequentWordTest_2(){
        TextAnalyzer textAnalyzer = new TextAnalyzer("");

        textAnalyzer.getMostFrequentWord();
    }
}
