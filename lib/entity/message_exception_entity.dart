import 'exception_entity.dart';

class MessageExceptionEntity {
  final String? id;
  final ExceptionEntity? err;

  MessageExceptionEntity({
    this.id,
    this.err,
  });

  factory MessageExceptionEntity.fromJson(Map<String, dynamic> json) {
    return MessageExceptionEntity(
      id: json['id'],
      err: json['err'] == null ? null : ExceptionEntity.fromJson(json['err']),
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'err': err == null ? null : err!.toJson(),
      };
}
