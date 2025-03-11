package miu.edu.demo.entities;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Date;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Logger {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    @Temporal(TemporalType.DATE)
    Date date;
    Long time;
    String principle;
    String operation;



}