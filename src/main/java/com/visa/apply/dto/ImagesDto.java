package com.visa.apply.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.visa.apply.entity.Images}
 */
public record ImagesDto(Long id, Byte imDevicetype, Short imWidth, Short imHeight, Short imImglen,
                        String imImage) implements Serializable {
}