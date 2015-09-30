package com.example.android.apis.justjava;

/**
 * メッセージ状態.
 */
public enum MessageStatus {

    /**
     * 通常
     */
    RECEIVED(1),

    /**
     * 画像アップロード中
     */
    UPLOADING_IMG(2),

    /**
     * 送信中
     */
    SENDING(3),

    /**
     * 送信成功
     */
    SENT_SUCCEEDED(4),

    /**
     * 削除
     */
    DELETED(5),

    /**
     * 画像アップロード失敗
     */
    UPLOAD_PICTURE_FAILED(1001),

    /**
     * メッセージ送信API失敗
     */
    SEND_FAILED(1004),

    /**
     * メッセージ送信API失敗して、自動再送信もう行っていない
     */
    SEND_FAILED_NO_RETRY(1005),;

    /**
     * 値
     */
    private final int value;

    /**
     * コンストラクタ.
     *
     * @param value 値
     */
    private MessageStatus(
            final int value) {

        this.value = value;
    }

    /**
     * 列挙型変換処理.
     *
     * @param value 値
     * @return 列挙型
     */
    public static MessageStatus toEnum(final int value) {
        for (MessageStatus target : values()) {
            if (target.value == value) {
                return target;
            }
        }
        return null;
    }

    /**
     * 値取得処理.
     *
     * @return 値
     */
    public int getValue() {
        return value;
    }
}
