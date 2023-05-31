package com.masai.service;

import java.util.List;
import java.util.stream.Collectors;

import com.masai.dao.IMedicineDAO;
import com.masai.dao.MedicineDAOImp;
import com.masai.dto.Medicine;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class MedicineServiceImp implements IMedicineService {

	@Override
	public void addMedicine(Medicine medicine) throws SomethingWentWrongException {
		IMedicineDAO medDAO = new MedicineDAOImp();
		medDAO.addMedicine(medicine);

	}

	@Override
	public void updateMedicine(Medicine medicine, String id) throws SomethingWentWrongException, NoRecordFoundException {
		IMedicineDAO medDAO = new MedicineDAOImp();
		medDAO.updateMedicine(medicine, id);
	}

	@Override
	public void deleteMedicine(String medId) throws SomethingWentWrongException {
		IMedicineDAO medDAO = new MedicineDAOImp();
		medDAO.deleteMedicine(medId);
	}

	@Override
	public List<Medicine> getAllMedicines() throws SomethingWentWrongException, NoRecordFoundException {
		IMedicineDAO medDAO = new MedicineDAOImp();
		return medDAO.getMedicineList();
	}

	@Override
	public Medicine getMedicineById(String medId) throws NoRecordFoundException, SomethingWentWrongException {
		IMedicineDAO medDAO = new MedicineDAOImp();
		return medDAO.searchMedById(medId);
	}

	@Override
	public List<Medicine> getExpiredMedicines() throws SomethingWentWrongException, NoRecordFoundException {
		IMedicineDAO medDAO = new MedicineDAOImp();
		return medDAO.searchExpMeds();
	}

}
