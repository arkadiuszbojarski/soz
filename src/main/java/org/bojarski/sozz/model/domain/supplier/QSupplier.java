package org.bojarski.sozz.model.domain.supplier;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSupplier is a Querydsl query type for Supplier
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSupplier extends EntityPathBase<Supplier> {

    private static final long serialVersionUID = 17244122L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSupplier supplier = new QSupplier("supplier");

    public final org.bojarski.sozz.model.domain.contact.QContact contact;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSupplier(String variable) {
        this(Supplier.class, forVariable(variable), INITS);
    }

    public QSupplier(Path<? extends Supplier> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSupplier(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSupplier(PathMetadata<?> metadata, PathInits inits) {
        this(Supplier.class, metadata, inits);
    }

    public QSupplier(Class<? extends Supplier> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contact = inits.isInitialized("contact") ? new org.bojarski.sozz.model.domain.contact.QContact(forProperty("contact"), inits.get("contact")) : null;
    }

}

