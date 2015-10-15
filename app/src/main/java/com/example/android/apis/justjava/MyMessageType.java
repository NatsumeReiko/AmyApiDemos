package com.example.android.apis.justjava;

/**
 * messageタイプ.
 */
public enum MyMessageType {

    /** テキスト */
    TEXT(1),

    /** 画像 */
    PICTURE(2),

    /** スタンプ */
    STAMP(3),

    EMOTION_STAMP(201),

    OMIKUJI(202),

    CLUB_INVITATION(203),

    ACTIVITY_MESSAGE(999),

    ;

    /** 値 */
    private final int value;

    /**
     * コンストラクタ.
     * @param value 値
     */
    private MyMessageType(
            final int value) {

        this.value = value;
    }

    /**
     * 列挙型変換処理.
     * @param value 値
     * @return 列挙型
     */
    public static MyMessageType toEnum(final int value) {
        for (MyMessageType target : values()) {
            if (target.value == value) {
                return target;
            }
        }
        return null;
    }

    /**
     * 値取得処理.
     * @return 値
     */
    public int getValue() {
        return value;
    }
}
