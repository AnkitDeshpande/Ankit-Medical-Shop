package com.masai.dao;

import java.util.List;

import com.masai.dto.*;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface IMedicineDAO {
	void addMedicine(Medicine medicine) throws SomethingWentWrongException;

	List<Medicine> getMedicineList() throws SomethingWentWrongException, NoRecordFoundException;

	void updateMedicine(Medicine medicine, String id) throws SomethingWentWrongException;

	void deleteMedicine(String medid) throws SomethingWentWrongException;

	Medicine searchMedById(String medId) throws SomethingWentWrongException;

	List<Medicine> searchExpMeds() throws SomethingWentWrongException, NoRecordFoundException;

}
