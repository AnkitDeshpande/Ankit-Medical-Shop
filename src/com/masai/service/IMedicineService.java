package com.masai.service;

import java.util.List;

import com.masai.dto.Medicine;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface IMedicineService {
	void addMedicine(Medicine medicine) throws SomethingWentWrongException;

	void updateMedicine(Medicine medicine, String id) throws SomethingWentWrongException, NoRecordFoundException;

	void deleteMedicine(String medId) throws SomethingWentWrongException;

	List<Medicine> getAllMedicines() throws SomethingWentWrongException, NoRecordFoundException;

	Medicine getMedicineById(String medId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Medicine> getExpiredMedicines() throws SomethingWentWrongException, NoRecordFoundException;
}
