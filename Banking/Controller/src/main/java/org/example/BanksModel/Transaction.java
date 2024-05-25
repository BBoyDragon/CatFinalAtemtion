package org.example.BanksModel;

import lombok.Getter;
import org.example.ModelAbstractions.IBankController;
import org.example.ModelAbstractions.ITransaction;

public class Transaction implements ITransaction {
public Transaction(IBankController fromBank, int fromBankId,IBankController toBank,int toBankId,float amount,int id){
    _fromBank=fromBank;
    _fromBankId=fromBankId;
    _toBank=toBank;
    _toBankId=toBankId;
    _amount=amount;
    _id=id;
}
    private IBankController _fromBank;
    private int _fromBankId;
    private IBankController _toBank;
    private int _toBankId;
    private float _amount;
    private Boolean _isActivated=false;
    @Getter
    private int _id;
    @Getter
    private Boolean Local=false;
    @Override
    public TransactionResult Do() {
        if (_isActivated){
            return TransactionResult.Failure;
        }
        if(_fromBank.RemoveMoneyFromAccount(_fromBankId,_amount,_id) == BankOperationResult.Failure){
            return TransactionResult.Failure;
        }
        if(_toBank.AddMoneyOnAccount(_toBankId,_amount, _id) ==BankOperationResult.Failure){
            _fromBank.AddMoneyOnAccount(_fromBankId,_amount, _id);
            return TransactionResult.Failure;
        }
        _isActivated=true;
        return TransactionResult.Success;
    }

    @Override
    public ITransaction GetUndo() {
        return new Transaction(_toBank,_toBankId,_fromBank,_fromBankId,_amount,_id);
    }
}
