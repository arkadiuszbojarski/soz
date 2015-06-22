package org.bojarski.sozz.model.domain.unit;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUnit is a Querydsl query type for Unit
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUnit extends EntityPathBase<Unit> {

    private static final long serialVersionUID = -517582886L;

    public static final QUnit unit = new QUnit("unit");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QUnit(String variable) {
        super(Unit.class, forVariable(variable));
    }

    public QUnit(Path<? extends Unit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUnit(PathMetadata<?> metadata) {
        super(Unit.class, metadata);
    }

}

