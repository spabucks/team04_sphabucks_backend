package sphabucks.coupon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Coupon {

    @Id
    private Long id;
    private String name;
    private Date start_date;
    private Date end_date;
    private String image;
    private String qr_image;
    private String content;
    private String number;
}
