package org.bojarski.sozz.model.domain.part;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPart is a Querydsl query type for Part
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPart extends EntityPathBase<Part> {

    private static final long serialVersionUID = -1854505670L;

    private static final PathInits INITS = new PathInits("*", "supplier.contact.*");

    public static final QPart part = new QPart("part");

    public final org.bojarski.sozz.model.domain.category.QCategory category;

    public final StringPath description = createString("description");

    public final CollectionPath<org.bojarski.sozz.model.domain.dimension.Dimension, org.bojarski.sozz.model.domain.dimension.QDimension> dimensions = this.<org.bojarski.sozz.model.domain.dimension.Dimension, org.bojarski.sozz.model.domain.dimension.QDimension>createCollection("dimensions", org.bojarski.sozz.model.domain.dimension.Dimension.class, org.bojarski.sozz.model.domain.dimension.QDimension.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath material = createString("material");

    public final StringPath number = createString("number");

    public final org.bojarski.sozz.model.domain.supplier.QSupplier supplier;

    public QPart(String variable) {
        this(Part.class, forVariable(variable), INITS);
    }

    public QPart(Path<? extends Part> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPart(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPart(PathMetadata<?> metadata, PathInits inits) {
        this(Part.class, metadata, inits);
    }

    public QPart(Class<? extends Part> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new org.bojarski.sozz.model.domain.category.QCategory(forProperty("category")) : null;
        this.supplier = inits.isInitialized("supplier") ? new org.bojarski.sozz.model.domain.supplier.QSupplier(forProperty("supplier"), inits.get("supplier")) : null;
    }

}

