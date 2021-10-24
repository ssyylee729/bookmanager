package com.fastcampus.jpa.bookmanager.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

//    private Long bookId;
    @OneToOne(optional = false)
    private Book book;

    private float avgReviewScore;

    private int reviewCount;
/*primitive type으로 선언한 이유 (small float, small int)
* - null을 허용할 것인지 아닌지에 따라서 Wrapper or primitive 타입 결정
* - 기본값을 0으로 할 경우 primitive type 사용
* - Wrapper type은 null도 가능하여, null check 안하는 이상 null pointer exception 발생할 수 있음.*/
}


