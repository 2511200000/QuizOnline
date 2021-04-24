/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import khanhhq.dtos.TblQuestionDTO;
 
/**
 *
 * @author Administrator
 */
public class CartObject implements Serializable {

    private Map<String, TblQuestionDTO> questions;

    public Map<String, TblQuestionDTO> getQuestions() {
        return questions;
    }

    public void addtemToCart(TblQuestionDTO dto) {
        if (this.questions == null) {
            this.questions = new HashMap<>();
        }
        if (dto != null) {
            this.questions.put(dto.getId(), dto);
        }
    }

    public float TongDiem() {

        float diem = 0;
        if (this.questions == null) {
            return diem;
        }
        double i = 1;
        int count = 0;
        for (TblQuestionDTO dto : this.questions.values()) {
             count++;
            diem = (float) (count * i);
                         
        }
        return diem;
    }

    public int Correct() {
        int count = 0;
        if (this.questions == null) {
            return count;
        } else {
            for (TblQuestionDTO dto : this.questions.values()) {
                if (dto !=null) {
                    count++;
                } else {
                    count = 0;
                }
            }
        }

        return count;
    }
}
