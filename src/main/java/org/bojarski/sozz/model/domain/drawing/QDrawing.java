package org.bojarski.sozz.model.domain.drawing;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDrawing is a Querydsl query type for Drawing
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDrawing extends EntityPathBase<Drawing> {

    private static final long serialVersionUID = 1105733946L;

    public static final QDrawing drawing = new QDrawing("drawing");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath number = createString("number");

    public QDrawing(String variable) {
        super(Drawing.class, forVariable(variable));
    }

    public QDrawing(Path<? extends Drawing> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDrawing(PathMetadata<?> metadata) {
        super(Drawing.class, metadata);
    }

}

