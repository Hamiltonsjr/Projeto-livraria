FROM golang

MAINTAINER hamiltonsjr

WORKDIR /app/src/hamilton/ms-stockRegister

ENV GOPATH=/app

COPY . /app/src/hamilton/ms-stockRegister

RUN ls -a

RUN chmod +x main 

ENTRYPOINT [ "./main" ]

EXPOSE 8001