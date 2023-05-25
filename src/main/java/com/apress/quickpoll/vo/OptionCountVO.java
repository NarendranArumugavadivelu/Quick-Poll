package com.apress.quickpoll.vo;

import java.io.Serializable;

public record OptionCountVO(Long optionId, int count) implements Serializable {

}
