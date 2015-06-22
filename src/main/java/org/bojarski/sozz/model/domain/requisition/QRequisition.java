package org.bojarski.sozz.model.domain.requisition;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRequisition is a Querydsl query type for Requisition
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRequisition extends EntityPathBase<Requisition> {

    private static final long serialVersionUID = -843955914L;

    private static final PathInits INITS = new PathInits("*", "part.*.*.*");

    public static final QRequisition requisition = new QRequisition("requisition");

    public final NumberPath<Double> amount = createNumber("amount", Double.class);

    public final org.bojarski.sozz.model.domain.account.QAccount author;

    public final StringPath comment = createString("comment");

    public final org.bojarski.sozz.model.domain.drawing.QDrawing drawing;

    public final StringPath element = createString("element");

    public final DateTimePath<java.util.Date> end = createDateTime("end", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> number = createNumber("number", Long.class);

    public final org.bojarski.sozz.model.domain.part.QPart part;

    public final DateTimePath<java.util.Date> start = createDateTime("start", java.util.Date.class);

    public final EnumPath<Status> status = createEnum("status", Status.class);

    public final org.bojarski.sozz.model.domain.unit.QUnit unit;

    public QRequisition(String variable) {
        this(Requisition.class, forVariable(variable), INITS);
    }

    public QRequisition(Path<? extends Requisition> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRequisition(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRequisition(PathMetadata<?> metadata, PathInits inits) {
        this(Requisition.class, metadata, inits);
    }

    public QRequisition(Class<? extends Requisition> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new org.bojarski.sozz.model.domain.account.QAccount(forProperty("author")) : null;
        this.drawing = inits.isInitialized("drawing") ? new org.bojarski.sozz.model.domain.drawing.QDrawing(forProperty("drawing")) : null;
        this.part = inits.isInitialized("part") ? new org.bojarski.sozz.model.domain.part.QPart(forProperty("part"), inits.get("part")) : null;
        this.unit = inits.isInitialized("unit") ? new org.bojarski.sozz.model.domain.unit.QUnit(forProperty("unit")) : null;
    }

}

