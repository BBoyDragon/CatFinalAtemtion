package org.example.BanksModel;

import lombok.Getter;
import org.example.AccountsModel.AccountOperationResult;
import org.example.ModelAbstractions.IBankController;
import org.example.ModelAbstractions.ITransaction;

public class RemoveMoneyTransaction implements ITransaction {
    private IBankController _bank;
    private int _accountId;
    private float _amount;
    private Boolean _isActivated=false;
    @Getter
    private int _id;
    @Getter
    private Boolean local=true;

    public RemoveMoneyTransaction(IBankController bank, int accountId,float amount,int id){
        _bank = bank;
        _accountId = accountId;
        _amount=amount;
        _id=id;
    }
    @Override
    public TransactionResult Do() {
        if (_isActivated){
            return TransactionResult.Failure;
        }
        if(_bank.GetAccount(_accountId).RemoveMoney(_amount)== AccountOperationResult.success)
        {
            return TransactionResult.Success;
        }
        _isActivated=true;
        return TransactionResult.Failure;
    }

    @Override
    public ITransaction GetUndo() {
        return new AddTransaction(_bank,_accountId, _amount,_id);
    }
}