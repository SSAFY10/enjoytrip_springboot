package com.ssafy.enjoytrip.attraction.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.attraction.model.dto.GugunDto;
import com.ssafy.enjoytrip.attraction.model.dto.SidoDto;

@Mapper
public interface AttractionDao {

	int totalAttractionListCount(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> search(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);

	List<SidoDto> searchAllSido();

	List<GugunDto> searchGugunBySido(int sidoCode);

	List<AttractionInfoDto> searchWithPaging(AttractionInfoDto attractionInfoDto, int start, int sizePerPage);

}
