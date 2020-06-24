package com.ajiani.maidahui.Utils;

import com.ajiani.maidahui.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmotionUtils {
    public static Map<Integer, Integer> emojiMap;
     public static ArrayList<String> list;
    static {
        emojiMap = new HashMap<>();
        emojiMap.put(1, R.mipmap.expression_one);
        emojiMap.put(2, R.mipmap.expression_two);
        emojiMap.put(3, R.mipmap.expression_three);
        emojiMap.put(4, R.mipmap.expression_four);
        emojiMap.put(5, R.mipmap.expression_five);
        emojiMap.put(6, R.mipmap.expression_six);
        emojiMap.put(7, R.mipmap.expression_seven);
        emojiMap.put(8, R.mipmap.expression_eight);
        emojiMap.put(9, R.mipmap.expression_nine);
        emojiMap.put(10, R.mipmap.expression_ten);
        emojiMap.put(11, R.mipmap.expression_eleven);
        emojiMap.put(12, R.mipmap.expression_eight);
        emojiMap.put(13, R.mipmap.expression_nine);
        emojiMap.put(14, R.mipmap.expression_ten);
        emojiMap.put(15, R.mipmap.expression_eleven);
        emojiMap.put(16, R.mipmap.expression_eight);
        emojiMap.put(17, R.mipmap.expression_nine);
        emojiMap.put(18, R.mipmap.expression_ten);
        emojiMap.put(19, R.mipmap.expression_eleven);
        emojiMap.put(20, R.mipmap.expression_eight);
        emojiMap.put(21, R.mipmap.expression_nine);
        emojiMap.put(22, R.mipmap.expression_ten);
        emojiMap.put(23, R.mipmap.expression_eleven);
        emojiMap.put(24, R.mipmap.expression_ten);
        emojiMap.put(25, R.mipmap.expression_eleven);
        emojiMap.put(26, R.mipmap.expression_eight);
        emojiMap.put(27, R.mipmap.expression_nine);
        emojiMap.put(28, R.mipmap.expression_ten);
        emojiMap.put(29, R.mipmap.expression_eleven);
        emojiMap.put(30, R.mipmap.expression_eight);
        emojiMap.put(31, R.mipmap.expression_nine);
        emojiMap.put(32, R.mipmap.expression_ten);
        emojiMap.put(33, R.mipmap.expression_eleven);
        emojiMap.put(34, R.mipmap.expression_eight);
        emojiMap.put(35, R.mipmap.expression_nine);
        emojiMap.put(36, R.mipmap.expression_ten);
        emojiMap.put(37, R.mipmap.expression_eleven);

        list=new ArrayList<>();
        list.add("[色]");
        list.add("[开心]");
        list.add("[无语]");
        list.add("[闭嘴]");
        list.add("[呲牙]");
        list.add("[笑哭]");
        list.add("[惊讶]");
        list.add("[大笑]");
        list.add("[白眼]");
        list.add("[惊恐]");
        list.add("[大哭]");
        list.add("[不好]");
        list.add("[握手]");
        list.add("[惊讶]");
        list.add("[大笑]");
        list.add("[白眼]");
        list.add("[惊恐]");
        list.add("[大哭]");
        list.add("[不好]");
        list.add("[握手]");
        list.add("[惊讶]");
        list.add("[大笑]");
        list.add("[白眼]");
        list.add("[惊恐]");
        list.add("[大哭]");
        list.add("[不好]");
        list.add("[握手]");
        list.add("[惊讶]");
        list.add("[大笑]");
        list.add("[白眼]");
        list.add("[惊恐]");
        list.add("[大哭]");
        list.add("[不好]");
        list.add("[握手]");
        list.add("[惊讶]");
        list.add("[大哭]");
        list.add("[不好]");
        list.add("[握手]");
        list.add("[惊讶]");
        list.add("[大哭]");
        list.add("[不好]");
        list.add("[握手]");
        list.add("[惊讶]");
    }

    public static int getImgByName(String imgName) {
        Integer integer = emojiMap.get(imgName);
        return integer == null ? -1 : integer;
    }
}
