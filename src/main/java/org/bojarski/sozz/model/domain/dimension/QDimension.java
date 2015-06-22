package org.bojarski.sozz.model.domain.dimension;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDimension is a Querydsl query type for Dimension
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QDimension extends BeanPath<Dimension> {

    private static final long serialVersionUID = 637007818L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDimension dimension = new QDimension("dimension");

    public final StringPath name = createString("name");

    public final org.bojarski.sozz.model.domain.unit.QUnit unit;

    public final NumberPath<Double> value = createNumber("value", Double.class);

    public QDimension(String variable) {
        this(Dimension.class, forVariable(variable), INITS);
    }

    public QDimension(Path<? extends Dimension> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDimension(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDimension(PathMetadata<?> metadata, PathInits inits) {
        this(Dimension.class, metadata, inits);
    }

    public QDimension(Class<? extends Dimension> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.unit = inits.isInitialized("unit") ? new org.bojarski.sozz.model.domain.unit.QUnit(forProperty("unit")) : null;
    }

}

