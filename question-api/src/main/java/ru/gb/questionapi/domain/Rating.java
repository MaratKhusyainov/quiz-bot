package ru.gb.questionapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "daily_rating")
    private Long dailyRating;

    @Column(name = "weekly_rating")
    private Long weeklyRating;

    @Column(name = "monthly_rating")
    private Long monthlyRating;

    @Column(name = "total_rating")
    private Long totalRating;
}
