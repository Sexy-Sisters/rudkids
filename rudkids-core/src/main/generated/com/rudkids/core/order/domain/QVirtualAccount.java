package com.rudkids.core.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVirtualAccount is a Querydsl query type for VirtualAccount
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QVirtualAccount extends BeanPath<VirtualAccount> {

    private static final long serialVersionUID = 2107592202L;

    public static final QVirtualAccount virtualAccount = new QVirtualAccount("virtualAccount");

    public final StringPath accountNumber = createString("accountNumber");

    public final EnumPath<BankCode> bankCode = createEnum("bankCode", BankCode.class);

    public final StringPath customerName = createString("customerName");

    public final StringPath dueDate = createString("dueDate");

    public final StringPath refundAccountName = createString("refundAccountName");

    public final StringPath refundBankCode = createString("refundBankCode");

    public final StringPath refundHolderName = createString("refundHolderName");

    public QVirtualAccount(String variable) {
        super(VirtualAccount.class, forVariable(variable));
    }

    public QVirtualAccount(Path<? extends VirtualAccount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVirtualAccount(PathMetadata metadata) {
        super(VirtualAccount.class, metadata);
    }

}

