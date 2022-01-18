package com.example.demo.common.vo;

/**
 * @author banyue
 * date 2020-06-07
 */
public class CaptchaVO {

    /**
     * 验证码缓存时间
     */
    private int captchaTimeout;
    /**
     * 有效性
     */
    private Boolean validity;
    /**
     * 验证码redis存储key,每个验证码验证完就删除
     */
    private String codeKey;
    /**
     * 验证码图片
     */
    private String captchaPic;

    public int getCaptchaTimeout() {
        return captchaTimeout;
    }

    public void setCaptchaTimeout(int captchaTimeout) {
        this.captchaTimeout = captchaTimeout;
    }

    public Boolean getValidity() {
        return validity;
    }

    public void setValidity(Boolean validity) {
        this.validity = validity;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public String getCaptchaPic() {
        return captchaPic;
    }

    public void setCaptchaPic(String captchaPic) {
        this.captchaPic = captchaPic;
    }
}
