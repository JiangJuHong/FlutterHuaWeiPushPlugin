class ExceptionEntity {
  final int? code;
  final String? message;

  ExceptionEntity({
    this.code,
    this.message,
  });

  factory ExceptionEntity.fromJson(Map<String, dynamic> json) {
    return ExceptionEntity(
      code: json['code'],
      message: json['message'],
    );
  }

  Map<String, dynamic> toJson() => {
        'code': code,
        'message': message,
      };
}
