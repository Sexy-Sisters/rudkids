package com.rudkids.core.order.domain;

import com.rudkids.core.order.exception.BankCodeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BankCode {
    KYONGNAMBANK("경남은행", "39"),
    GWANGJUBANK("광주은행", "34"),
    LOCALNONGHYEOP("단위농협(지역농축협)", "12"),
    BUSANBANK("부산은행", "32"),
    SAEMAUL("새마을금고", "45"),
    SANLIM("산림조합", "64"),
    SHINHAN("신한은행", "88"),
    SHINHYEOP("신협", "39"),
    CITI("씨티은행", "27"),
    WOORI("우리은행", "20"),
    POST("우체국예금보험", "71"),
    SAVINGBANK("저축은행중앙회", "50"),
    JEONBUKBANK("전북은행", "37"),
    JEJUBANK("제주은행", "35"),
    KAKAOBANK("카카오뱅크", "90"),
    KBANK("케이뱅크", "89"),
    TOSSBANK("토스뱅크", "92"),
    HANA("하나은행", "81"),
    HSBC("홍콩상하이은행", "54"),
    IBK("IBK기업은행", "03"),
    KOOKMIN("KB국민은행", "06"),
    DAEGUBANK("DGB대구은행", "31"),
    KDBBANK("KDB산업은행", "02"),
    NONGHYEOP("NH농협은행", "11"),
    SC("SC제일은행", "23"),
    SUHYEOP("Sh수협은행", "07"),
    EMPTY("없음", "00");

    private final String name;
    private final String code;

    public static BankCode toEnumByCode(String code) {
        return Arrays.stream(values())
            .filter(bankCode -> bankCode.isSameCode(code))
            .findFirst()
            .orElse(EMPTY);
    }

    public static BankCode toEnumByName(String name) {
        return Arrays.stream(values())
            .filter(bankCode -> bankCode.isSameName(name))
            .findFirst()
            .orElseThrow(BankCodeNotFoundException::new);
    }

    private boolean isSameCode(String code) {
        return this.code.equals(code);
    }

    private boolean isSameName(String name) {
        return this.name.equals(name);
    }
}
