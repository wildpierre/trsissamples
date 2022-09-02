/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.stepanoff.sample.kafka;

import javax.transaction.Transactional;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author stepanov.pa
 */
@Service
public class KafkaConsumerListeners {

    @Autowired
    SchoolRepository schoolRepository;

    @Transactional
    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupid}")
    public void debeziumListener1(ConsumerRecord<org.apache.avro.generic.GenericData.Record, org.apache.avro.generic.GenericData.Record> record) {

        if (record.value() == null) {
            return;
        }

        String op = ((org.apache.avro.util.Utf8) record.value().get("op")).toString();

        if (op.equals("d")) {
            Integer idToDelete = (Integer) record.key().get("school_id");
            try {
                schoolRepository.deleteById(idToDelete);
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {
                //Команда удаления приходит из удаленного лога репликации
                //в том числе и в ответ на ответ на удаление в локальном. 
                //В этом случае удалить повторно запись уже нельзя
            }
            return;
        }

        org.apache.avro.generic.GenericData.Record value = (org.apache.avro.generic.GenericData.Record) record.value().get("after");
        Integer id = (Integer) value.get("school_id");
        String name = ((org.apache.avro.util.Utf8) value.get("school_name")).toString();
        Integer number = (Integer) value.get("school_number");

        School school = schoolRepository.findById(id).orElse(null);

        if (school == null) {
            schoolRepository.save(new School(id, name, number));
        } else {
            school.setName(name);
            school.setNumber(number);
            schoolRepository.save(school);
        }
    }

}
