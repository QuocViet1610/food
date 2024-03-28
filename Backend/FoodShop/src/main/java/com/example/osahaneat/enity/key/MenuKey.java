package com.example.osahaneat.enity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MenuKey  implements Serializable {
    @Column(name = "cate_id")
    private int cateId;
    @Column(name = "res_id")
    private int resId;
    public MenuKey() {
    }
    public MenuKey(int cateId, int resId) {
        this.cateId = cateId;
        this.resId = resId;
    }
}
