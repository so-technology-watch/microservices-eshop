FROM lechelong/eshop-basex64
RUN apk --update add python3-dev build-base linux-headers pcre-dev
ADD ./data /data
WORKDIR ./data
RUN ls -g
RUN pip3 install -r requirements.txt
EXPOSE 5000
CMD python3 app.py
