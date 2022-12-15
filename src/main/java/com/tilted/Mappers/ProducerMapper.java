package com.tilted.Mappers;

import com.tilted.Models.Producer;
import com.tilted.Models.ProducerDTO;
import com.tilted.Models.ProductDTO;

public final class ProducerMapper {
    public static ProducerDTO ToDTO(Producer producer){
        var producerDto = new ProducerDTO();
        producerDto.Id = producer.getId();
        producerDto.Name = producer.getName();
        producerDto.Place = producer.getPlace();
        return producerDto;
    }

    public static Producer ToModel(ProducerDTO producerDTO){
        var producer = new Producer();
        producer.setId(producerDTO.Id);
        producer.setName(producerDTO.Name);
        producer.setPlace(producerDTO.Place);
        return producer;
    }
}
